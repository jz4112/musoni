package forms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LoanApplicationForm extends Form {

  private static final long serialVersionUID = 1L;

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

  public String getJSONSubmissionQuery() {
    String[] possibleFields = { "clientId", "productId", "principal",
        "loanTermFrequency", "loanTermFrequencyType", "loanType",
        "numberOfRepayments", "repaymentEvery", "repaymentFrequencyType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestCalculationPeriodType", "transactionProcessingStrategyId",
        "graceOnPrincipalPayment", "graceOnInterestPayment",
        "graceOnInterestCharged", "linkAccountId" };

    StringBuilder strB = new StringBuilder();

    // set dateformat
    strB.append("\t\"dateFormat\": \"dd\\MM\\yyyy\"");
    for (String str : possibleFields) {
      if (fields.containsKey(str)) {
        strB.append("\t\"" + str + "\": " + fields.get(str)[0] + ",\n");
      }
    }
    strB.append("}\n");
    return strB.toString();

  }

}
