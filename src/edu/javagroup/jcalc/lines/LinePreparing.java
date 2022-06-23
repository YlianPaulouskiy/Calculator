package edu.javagroup.jcalc.lines;

/**
 * Класс подготавливает(редактирует) математическое выражение.
 */
public class LinePreparing {

    /**
     * Обрабатывает пришедшую строку, подготавливает к вычислениям.
     *
     * @param source Математическое выражение(String).
     * @return Отредактированное математическое выражение(String).
     */
    public static String linePreparing(String source) {
        // если строка содержит пробелы, удаляем их
        source = source.contains(" ") ? removeSpaces(source) : source;
        // если строка содержит запятые, заменяем их на точки
        source = source.contains(",") ? replaceCommas(source) : source;
        // оставляем в строке только математические символы
        source = leaveMathSymbols(source);
        // если строка начинается + то удаляем его
        source = source.startsWith("+") ? source.substring(1) : source;
        return source;
    }

    /**
     * Метод удаляет все пробелы в строке.
     *
     * @param source Математическое выражение(String).
     * @return Математическое выражение без пробелов(String).
     */
    public static String removeSpaces(String source) {
        return source.replace(" ", "");
    }

    /**
     * Метод заменяет в строке все запятые на точки.
     *
     * @param source Математическое выражение(String).
     * @return Математическое выражение с замененными запятыми на точки(String).
     */
    public static String replaceCommas(String source) {
        return source.replace(",", ".");
    }

    /**
     * Метод оставляет в пришедшей строке только допустимые
     * символы 0123456789().*+-/, обрабатывает строку в методе trimTails
     * и removeDuplicates
     *
     * @param source Математическое выражение(String).
     * @return Обработанное на допустимые символы математическое выражение(String).
     */
    public static String leaveMathSymbols(String source) {
        // допустимые символы
        String mathSymbols = "0123456789.()*/+-";
        StringBuilder builderLine = new StringBuilder(source);
        // проверяем строку на допустимые символы, если его нет в допустимых, то удаляем его
        for (int i = 0; i < builderLine.length(); i++) {
            if (!mathSymbols.contains(Character.toString(builderLine.charAt(i)))) {
                builderLine.deleteCharAt(i);
                i--;
            }
        }
        return removeDuplicates(trimTails(builderLine.toString()));
    }

    /**
     * Метод удаляет лишние символы в начале и конце
     * математическго выражения, учитывая, первый минус
     * и наличие скобок в начале.
     *
     * @param source Математическое выражени(String).
     * @return Математическое выражение без лишних символов в начале и конце(String).
     */
    public static String trimTails(String source) {
        StringBuilder line = new StringBuilder(source);
        int i = 0;
        //удаление с начала строки
        while (!Character.isDigit(line.charAt(i)) && line.charAt(i) != '(' // если не число и не скобка
                && !(Character.isDigit(line.charAt(i + 1)) && line.charAt(i) == '-') // если минус и следующий символ число
                && !(line.charAt(i + 1) == '(' && line.charAt(i) == '-')) { // если минус и следующий символ скобка
            line.deleteCharAt(i);
        }
        //удаление символов с конца
        i = line.length() - 1;
        while (!Character.isDigit(line.charAt(i)) && line.charAt(i) != ')') {
            line.deleteCharAt(i--);
        }
        return line.toString();
    }

    /**
     * Метод удаляет дубликаты подряд идущих математических символов.
     *
     * @param source Математическое выражение(String).
     * @return Математическое выражение без дулируемых подряд символов(String)
     */
    public static String removeDuplicates(String source) {
        //Заменяем некоторые выражения
        source = source.contains("--") ? source.replace("--", "+") : source;
        source = source.contains("-+") ? source.replace("-+", "-") : source;
        source = source.contains("+-") ? source.replace("+-", "-") : source;
        source = source.contains(")(") ? source.replace(")(", ")*(") : source;
        source = source.contains("()") ? source.replace("()", "") : source;
        StringBuilder builderLine = new StringBuilder(source);
        for (int i = 0; i < builderLine.length() - 1; i++) {
            //проверка чтобы не удалить идущие подрят числа
            if (!Character.isDigit(source.charAt(i)) && builderLine.charAt(i) == builderLine.charAt(i + 1)) {
                builderLine.deleteCharAt(i + 1);
                // уменьшаем i и j, т.к. длина выражения становится меньше
                i--;
            }
        }
        return builderLine.toString();
    }
}
