package forms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

public class LoanApplicationForm extends Form {

  private static final long serialVersionUID = 1L;

  // Iterates over all fields, ensuring they are well formed, and also
  // checks that all of the required fields are present.
  public boolean validateData() {
    Set<String> keySet = fields.keySet();
    String[] fieldsList = { "clientId", "productId", "principal",
        "loanTermFrequency", "loanTermFrequencyType", "loanType",
        "numberOfRepayments", "repaymentEvery", "repaymentFrequencyType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestCalculationPeriodType", "transactionProcessingStrategyId" };
    Set<String> mandatoryFields = new HashSet<String>(Arrays.asList(fieldsList));

    if (!keySet.containsAll(mandatoryFields)) {
      return false; // Not all mandatory fields are present.
    }

    return checkData();
  }

  // Builds the JSON message body to be sent to create a loan submission.
  public JSONObject getJSONSubmissionQuery() {
    String[] possibleFields = { "clientId", "productId", "principal",
        "loanTermFrequency", "loanTermFrequencyType", "loanType",
        "numberOfRepayments", "repaymentEvery", "repaymentFrequencyType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestCalculationPeriodType", "transactionProcessingStrategyId",
        "graceOnPrincipalPayment", "graceOnInterestPayment",
        "graceOnInterestCharged", "linkAccountId" };

    Map<String, String> presentPairs = new LinkedHashMap<String, String>();
    presentPairs.put("dateFormat", "\"dd\\MM\\yyyy\"");

    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        presentPairs.put(str, fields.get(str)[0]);
      }
    }

    return new JSONObject(presentPairs);
  }

}
