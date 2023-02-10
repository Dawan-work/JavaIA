package flux.lambda;

import objets.Chien;

public class Lambda {
    public static void main(String[] args) {
        CurrencyStringifier cs = new CurrencyStringifier() {
            @Override
            public String convert(double amount) {
                return String.format("%.2f €", amount);
            }
        };
        soutCurrencyAmount(cs);

        // Same as
        CurrencyStringifier currencyStringifier = amount -> String.format("%.2f $",amount);
        soutCurrencyAmount(currencyStringifier);

        // Same as
        soutCurrencyAmount(amount -> String.format("%.2f £", amount));

        // Method Reference
        double aDouble = 523.48;
        String amountString = String.valueOf(aDouble);
        soutCurrencyAmount(String::valueOf);
    }

    private static void soutCurrencyAmount(CurrencyStringifier strignifier) {
        double amount = 143.987652;
        System.out.println(strignifier.convert(amount));
    }
}
