package forms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LoanApplicationForm extends Form {

  public boolean validateData() {
    Set<String> keySet = fields.keySet();
    String[] fieldsList = { "clientId", "productId", "principal",
        "loanTermFrequency", "loanTermFrequencyType", "loanType",
        "numberOfRepayments", "repaymentEvery", "repaymentFrequencyType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestRatePerPeriod", "amortizationType", "interestType",
        "interestCalculationPeriodType", "transactionProcessingStrategyId" };
    Set<String> mandatoryFields = new HashSet<String>(Arrays.asList(fieldsList));

    if(!keySet.containsAll(mandatoryFields)) {
      return false; // Not all mandatory fields are present.
    }

    return checkData();
  }

}
