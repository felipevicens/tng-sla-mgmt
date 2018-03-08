package eu.tng.tng_sla_mgmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetPolicyRules {
	public void getPolicyRules() {
		PolicyRule setPolicyRuleFields = new PolicyRule();
		ArrayList<String> name_list = new ArrayList();
		ArrayList<String> field_list = new ArrayList();
		ArrayList<String> operator_list = new ArrayList();
		ArrayList<String> type_list = new ArrayList();
		ArrayList<String> value_list = new ArrayList();

		try {
			// test url to call the policy descriptor - when ready call the Policy Manager
			// to access the policy descriptors
			URL url = new URL("https://api.myjson.com/bins/virrd");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				JSONParser parser = new JSONParser();
				try {
					Object obj = parser.parse(output);
					JSONObject jsonObject = (JSONObject) obj;

					if (jsonObject.containsKey("policyRules")) {
						JSONArray policyRules = (JSONArray) jsonObject.get("policyRules");
						for (int i = 0; i < policyRules.size(); i++) {
							JSONObject policyRule = (JSONObject) policyRules.get(i);
							// get policy rule name
							if (policyRule.containsKey("actions")) {
								JSONArray actions = (JSONArray) policyRule.get("actions");
								for (int j = 0; j < actions.size(); j++) {
									JSONObject action = (JSONObject) actions.get(j);
									name_list.add((String) action.get("name"));
								}
								setPolicyRuleFields.setName(name_list);

							}
							// get actual policy rules
							if (policyRule.containsKey("conditions")) {
								JSONObject conditions = (JSONObject) policyRule.get("conditions");
								if (conditions.containsKey("rules")) {
									JSONArray rules = (JSONArray) conditions.get("rules");
									for (int k = 0; k < rules.size(); k++) {
										JSONObject rule = (JSONObject) rules.get(k);
										if (rule.containsKey("rules")) {
											JSONArray rules2 = (JSONArray) rule.get("rules");
											for (int l = 0; l < rules2.size(); l++) {
												JSONObject rule2 = (JSONObject) rules2.get(l);
												field_list.add((String) rule2.get("field"));
												operator_list.add((String) rule2.get("operator"));
												type_list.add((String) rule2.get("type"));
												value_list.add((String) rule2.get("value"));
											}
										} else {
											String field = (String) rule.get("field");
											String operator = (String) rule.get("operator");
											String type = (String) rule.get("type");
											String value = (String) rule.get("value");
										}
										setPolicyRuleFields.setField(field_list);
										setPolicyRuleFields.setOperator(operator_list);
										setPolicyRuleFields.setType(type_list);
										setPolicyRuleFields.setValue(value_list);

									}
								}
							}

						} // end of for loop for policyRules array
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}