package edu.javagroup.jcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kaa
 * @version 4.0
 */
public class JCalcTest {

    public static final Map<String, Class> CLASS_MAP;

    static {

        Map<String, Class> map = new LinkedHashMap<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        try {
            Class roundClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Round");
            if (roundClass != null) {
                map.put("0", roundClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class additionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Addition");
            if (additionClass != null) {
                map.put("1", additionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class divisionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Division");
            if (divisionClass != null) {
                map.put("2", divisionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class multiplicationClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Multiplication");
            if (multiplicationClass != null) {
                map.put("3", multiplicationClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class subtractionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Subtraction");
            if (subtractionClass != null) {
                map.put("4", subtractionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineCheckClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineCheck");
            if (lineCheckClass != null) {
                map.put("5", lineCheckClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineOperationClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineOperation");
            if (lineOperationClass != null) {
                map.put("6", lineOperationClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineParsingClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineParsing");
            if (lineParsingClass != null) {
                map.put("7", lineParsingClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class linePreparingClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LinePreparing");
            if (linePreparingClass != null) {
                map.put("8", linePreparingClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        map.put("9", All.class);
        CLASS_MAP = Collections.unmodifiableMap(map);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static final List<TestParameter> TEST_PARAMETER_LIST = Collections.unmodifiableList(
            new ArrayList<TestParameter>() {{
                add(new TestParameter<>("Addition", "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3", "2"}, "3+2", "5"));
                add(new TestParameter<>("Addition", "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3", "2.34"}, "3+2.34", "5.34"));
                add(new TestParameter<>("Addition", "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3.34", "2"}, "3.34+2", "5.34"));
                add(new TestParameter<>("Addition", "addition", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"3.34", "2.11"}, "3.34+2.11", "5.45"));

                add(new TestParameter<>("Division", "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"5", "3"}, "5/3", "1.67"));
                add(new TestParameter<>("Division", "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2", "1.1"}, "2/1.1", "1.82"));
                add(new TestParameter<>("Division", "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2.2", "3"}, "2.2/3", "0.73"));
                add(new TestParameter<>("Division", "division", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"54.22", "18.02"}, "54.22/18.02", "3.01"));

                add(new TestParameter<>("Multiplication", "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"5", "3"}, "5*3", "15"));
                add(new TestParameter<>("Multiplication", "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2", "1.1"}, "2*1.1", "2.2"));
                add(new TestParameter<>("Multiplication", "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2.2", "3"}, "2.2*3", "6.6"));
                add(new TestParameter<>("Multiplication", "multiplication", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"54.22", "18.02"}, "54.22*18.02", "977.04"));

                add(new TestParameter<>("Subtraction", "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"5", "3"}, "5-3", "2"));
                add(new TestParameter<>("Subtraction", "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2", "1.1"}, "2-1.1", "0.9"));
                add(new TestParameter<>("Subtraction", "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"2.2", "3"}, "2.2-3", "-0.8"));
                add(new TestParameter<>("Subtraction", "subtraction", new Class[]{java.lang.String.class, java.lang.String.class}, new Object[]{"54.22", "18.02"}, "54.22-18.02", "36.2"));

                add(new TestParameter<>("LineCheck", "isFirstMathSymbol", null, new Object[]{"+1", "+"}, "+1 и +", false));
                add(new TestParameter<>("LineCheck", "isFirstMathSymbol", null, new Object[]{"-1", "+"}, "-1 и +", true));
                add(new TestParameter<>("LineCheck", "isLastMathSymbol", null, new Object[]{"1+", "+"}, "1+ и +", false));
                add(new TestParameter<>("LineCheck", "isLastMathSymbol", null, new Object[]{"1-", "+"}, "1- и +", true));

                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"*1"}, "*1", false));
                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"1*"}, "1*", false));
                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"/1"}, "/1", false));
                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"1/"}, "1/", false));
                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"+1"}, "+1", false));
                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"1+"}, "1+", false));
                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"-1"}, "-1", true));
                add(new TestParameter<>("LineCheck", "isMathSymbolsCorrect", null, new Object[]{"1-"}, "1-", false));

                add(new TestParameter<>("LineCheck", "isRoundBracketsPositionsCorrect", null, new Object[]{"1)2(3"}, "1)2(3", false));
                add(new TestParameter<>("LineCheck", "isRoundBracketsPositionsCorrect", null, new Object[]{"1(2)3"}, "1(2)3", true));

                add(new TestParameter<>("LineCheck", "isRoundBracketsCountCorrect", null, new Object[]{"1(2)3)4"}, "1(2)3)4", false));
                add(new TestParameter<>("LineCheck", "isRoundBracketsCountCorrect", null, new Object[]{"1(2(3)4)5"}, "1(2(3)4)5", true));

                add(new TestParameter<>("LineCheck", "isRoundBracketsCorrect", null, new Object[]{"1234"}, "1234", true));
                add(new TestParameter<>("LineCheck", "isRoundBracketsCorrect", null, new Object[]{"1(2(3)"}, "1(2(3)", false));
                add(new TestParameter<>("LineCheck", "isRoundBracketsCorrect", null, new Object[]{"1(2(3)4)5"}, "1(2(3)4)5", true));

                add(new TestParameter<>("LineCheck", "isLineCorrect", null, new Object[]{"-1"}, "-1", true));
                add(new TestParameter<>("LineCheck", "isLineCorrect", null, new Object[]{"1234"}, "1234", true));
                add(new TestParameter<>("LineCheck", "isLineCorrect", null, new Object[]{"1(2(3)"}, "1(2(3)", false));
                add(new TestParameter<>("LineCheck", "isLineCorrect", null, new Object[]{"1(2(3)4)5"}, "1(2(3)4)5", true));

                add(new TestParameter<>("LineOperation", "collectLines", null, new Object[]{"1+2+4", "3", 1}, "1+2+4 и 3 и 1", "3+4"));
                add(new TestParameter<>("LineOperation", "collectLines", null, new Object[]{"1+(2+3)+4", "5", 2, 6}, "1+(2+3)+4 и 5 и 2 и 6", "1+5+4"));

                add(new TestParameter<>("LineOperation", "subtraction", null, new Object[]{"1+2-3+4", 3}, "1+2-3+4 и 3", "-1"));

                add(new TestParameter<>("LineOperation", "addition", null, new Object[]{"1-2+3-4", 3}, "1-2+3-4 и 3", "5"));

                add(new TestParameter<>("LineOperation", "division", null, new Object[]{"1-2/3-4", 3}, "1-2/3-4 и 3", "0.67"));

                add(new TestParameter<>("LineOperation", "multiplication", null, new Object[]{"1-2*3-4", 3}, "1-2*3-4 и 3", "6"));

                add(new TestParameter<>("LineOperation", "getResultWithoutRoundBrackets", null, new Object[]{"1+4"}, "1+4", "5"));

                add(new TestParameter<>("LineOperation", "getResultWithRoundBrackets", null, new Object[]{"1+(2+3)+4"}, "1+(2+3)+4", "10"));

                add(new TestParameter<>("LineOperation", "getResult", null, new Object[]{"1+1"}, "1+1", "2"));
                add(new TestParameter<>("LineOperation", "getResult", null, new Object[]{"2+2*2"}, "2+2*2", "6"));
                add(new TestParameter<>("LineOperation", "getResult", null, new Object[]{"(2+2)*2"}, "(2+2)*2", "8"));
                add(new TestParameter<>("LineOperation", "getResult", null, new Object[]{"-1-1"}, "-1-1", "-2"));
                add(new TestParameter<>("LineOperation", "getResult", null, new Object[]{"-1*2+3/4"}, "-1*2+3/4", "-1.25"));
                add(new TestParameter<>("LineOperation", "getResult", null, new Object[]{"-1*(2+3)/4"}, "-1*(2+3)/4", "-1.25"));

                add(new TestParameter<>("LineOperation", "concatLines", null, new Object[]{"1", "2"}, "1 и 2", "12"));

                add(new TestParameter<>("LineOperation", "addMinusPrefix", null, new Object[]{"1"}, "1", "-1"));

                add(new TestParameter<>("LineOperation", "removeLastSymbol", null, new Object[]{"1+2+34"}, "1+2+34", "1+2+3"));

                add(new TestParameter<>("LineParsing", "getNumberFromRightPart", null, new Object[]{"1-2", 1}, "1-2 и 1", "2"));
                add(new TestParameter<>("LineParsing", "getNumberFromRightPart", null, new Object[]{"1-2-3", 1}, "1-2-3 и 1", "2"));
                add(new TestParameter<>("LineParsing", "getNumberFromRightPart", null, new Object[]{"1.2+3.4*5.6/7.8", 7}, "1.2+3.4*5.6/7.8 и 7", "5.6"));
                add(new TestParameter<>("LineParsing", "getNumberFromRightPart", null, new Object[]{"1.2+3.4*(-5.6)/7.8", 7}, "1.2+3.4*(-5.6)/7.8 и 7", "-5.6"));

                add(new TestParameter<>("LineParsing", "getNumberFromLeftPart", null, new Object[]{"1-2", 1}, "1-2 и 1", "1"));
                add(new TestParameter<>("LineParsing", "getNumberFromLeftPart", null, new Object[]{"-1-2+3", 2}, "-1-2 и 2", "-1"));
                add(new TestParameter<>("LineParsing", "getNumberFromLeftPart", null, new Object[]{"-1-2*3", 4}, "-1-2*3 и 4", "2"));
                add(new TestParameter<>("LineParsing", "getNumberFromLeftPart", null, new Object[]{"1.2+3.4*5.6/7.8", 7}, "1.2+3.4*5.6/7.8 и 7", "3.4"));
                add(new TestParameter<>("LineParsing", "getNumberFromLeftPart", null, new Object[]{"1.2+(-3.4)*5.6/7.8", 10}, "1.2+(-3.4)*5.6/7.8 и 10", "-3.4"));

                add(new TestParameter<>("LineParsing", "findFirstMathSymbol", null, new Object[]{"1.2+3.4*5.6/7.8", 1}, "1.2+3.4*5.6/7.8 и 1", "*"));
                add(new TestParameter<>("LineParsing", "findFirstMathSymbol", null, new Object[]{"1.2+3.4*5.6/7.8", 2}, "1.2+3.4*5.6/7.8 и 2", "+"));
                add(new TestParameter<>("LineParsing", "findFirstMathSymbol", null, new Object[]{"1.2+3.4*5.6/7.8"}, "1.2+3.4*5.6/7.8", "*"));
                add(new TestParameter<>("LineParsing", "findFirstMathSymbol", null, new Object[]{"1.2+3.4-5.6"}, "1.2+3.4-5.6", "+"));

                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"0"}, "0", true));
                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"-1"}, "-1", true));
                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"-1.2"}, "-1.2", true));
                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"-.1.2."}, "-.1.2.", false));
                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"-1.2+3.4"}, "-1.2+3.4", false));
                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"(1)"}, "(1)", false));
                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"."}, ".", false));
                add(new TestParameter<>("LineParsing", "isFinalNumber", null, new Object[]{"x"}, "x", false));

                //add(new TestParameter<>("LinePreparing", "removeRoundBrackets", null, new Object[]{"(1)+(-2)+(-3)+(4)"}, "(1)+(-2)+(-3)+(4)", "1+(-2)+(-3)+4"));
                add(new TestParameter<>("LinePreparing", "removeDuplicates", null, new Object[]{"++++--+--///*//***()-)(++**"}, "++++--+--///*//***()-)(++**", "+/*/*-)*(+*"));
                add(new TestParameter<>("LinePreparing", "trimTails", null, new Object[]{"++++--+--1--1++//**"}, "++++--+--1--1++//**", "-1--1"));
                add(new TestParameter<>("LinePreparing", "trimTails", null, new Object[]{"++++--+--(1--1)+(1++1)++//**"}, "++++--+--(1--1)+(1++1)++//**", "-(1--1)+(1++1)"));
                add(new TestParameter<>("LinePreparing", "leaveMathSymbols", null, new Object[]{"+aaaa-1b+b1+cccc"}, "+aaaa-1b--b1+cccc", "-1+1"));
                add(new TestParameter<>("LinePreparing", "replaceCommas", null, new Object[]{"1,2+3,4"}, "1,2+3,4", "1.2+3.4"));
                add(new TestParameter<>("LinePreparing", "removeSpaces", null, new Object[]{"1 + 2 - 4"}, "1 + 2 - 4", "1+2-4"));
                add(new TestParameter<>("LinePreparing", "linePreparing", null, new Object[]{"-- // ++ -- 1 ++ ( ) ++ ( 2 - 4 ) ( 4 ** 5 ) ++"}, "-- // ++ -- 1 ++ ( ) ++ ( 2 - 4 ) ( 4 ** 5 ) ++", "-1+(2-4)*(4*5)"));
            }}
    );

    //------------------------------------------------------------------------------------------------------------------

    public void startTest() {
        if (!CLASS_MAP.isEmpty()) {
            System.out.println("--- Выбери класс ----------------------------------------------");
            for (Map.Entry<String, Class> item : CLASS_MAP.entrySet()) {
                System.out.println(item.getKey() + " - " + item.getValue().getSimpleName());
            }
            System.out.print("твой выбор: ");
            String classMenuItem = getMenuItem();
            if (classMenuItem.equals("9")) {
                for (Class item : CLASS_MAP.values()) {
                    if (item.getSimpleName().equalsIgnoreCase("round")) {
                        testRound(item);
                    } else {
                        List<TestParameter> list = TEST_PARAMETER_LIST
                                .stream()
                                .filter(value -> value.getClassName().equalsIgnoreCase(item.getSimpleName()))
                                .collect(Collectors.toList());
                        String lastName = "";
                        for (TestParameter testParameter : list) {
                            if (testParameter.getMethodName().equals(lastName)) {
                                continue;
                            } else {
                                lastName = testParameter.getMethodName();
                            }
                            if (testParameter.getResult() instanceof Boolean) {
                                testLineClassesByBooleanResult(item, testParameter.getMethodName());
                            } else if (testParameter.getMethodArgumentArray() == null) {
                                testLineClassesByStringResult(item, testParameter.getMethodName());
                            } else {
                                testMathClasses(item);
                            }
                        }
                    }
                }
            } else {
                Class testClass = CLASS_MAP.getOrDefault(classMenuItem, null);
                if (testClass == null) {
                    System.out.println("Не найден искомый класс или ошибка выбора");
                } else {
                    testClass(testClass);
                }
            }
        } else {
            System.out.println("Ни один класс для тестов не найден");
        }
    }

    private String getMenuItem() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }

    private void testClass(Class testClass) {
        switch (testClass.getSimpleName()) {
            case "Round":
                testRound(testClass);
                break;
            case "Addition":
            case "Division":
            case "Multiplication":
            case "Subtraction":
                testMathClasses(testClass);
                break;
            case "LineCheck":
                testLineCheck(testClass);
                break;
            case "LineParsing":
                testLineParsing(testClass);
                break;
            case "LineOperation":
            case "LinePreparing":
                testStringUtilsClass(testClass);
                break;
            default:
                System.out.println("Не найден искомый класс для теста методов");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private void testRound(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---------------------------------------------");
        Double result = (Double) testMethod(testClass, "round", new Object[]{1.10601});
        System.out.println((result != null && result == 1.11 ? "УСПЕХ : " : "ПРОВАЛ: ") + "Метод: round, отправлено: '1.10601', получено: '" + result + "', ожидаем: '1.11'");
    }

    private void testMathClasses(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---------------------------------------------");
        for (TestParameter testParameter : TEST_PARAMETER_LIST.stream().filter(item -> item.getClassName().equals(testClass.getSimpleName())).collect(Collectors.toList())) {
            String result = (String) testMethod(testClass, testParameter.getMethodName(), testParameter.getMethodArgumentArray(), testParameter.getMethodValueArray());
            System.out.println((testParameter.getResult().equals(result) ? "УСПЕХ : " : "ПРОВАЛ: ") + "Метод: " + testParameter.getMethodName() + ", отправлено: '" + testParameter.getValue() + "', получено: '" + result + "', ожидаем: '" + testParameter.getResult() + "'");
        }
    }

    private void testLineCheck(Class testClass) {
        Method[] methodArray = testClass.getDeclaredMethods();
        Arrays.sort(methodArray, Comparator.comparing(Method::getName));
        if (methodArray.length > 0) {
            System.out.println("--- Выбери метод ----------------------------------------------");
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(i + " - " + methodArray[i].getName());
            }
            System.out.print("твой выбор: ");
            String menuItem = getMenuItem();
            menuItem = menuItem == null ? "" : menuItem.replaceAll("[^\\d]", "");
            if (menuItem.length() > 0) {
                int menuNumber = Integer.parseInt(menuItem);
                if (menuNumber > methodArray.length - 1) {
                    System.out.println("Такого номера нет");
                    System.exit(0);
                }
                testLineClassesByBooleanResult(testClass, methodArray[menuNumber].getName());
            } else {
                System.out.println("Укажи номер");
                System.exit(0);
            }
        } else {
            System.out.println("В классе " + testClass.getSimpleName() + ", методы не обнаружены");
            startTest();
        }
    }

    private void testLineParsing(Class testClass) {
        Method[] methodArray = testClass.getDeclaredMethods();
        Arrays.sort(methodArray, Comparator.comparing(Method::getName));
        if (methodArray.length > 0) {
            System.out.println("--- Выбери метод ---------------------------------------------");
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(i + " - " + methodArray[i].getName());
            }
            System.out.print("твой выбор: ");
            String menuItem = getMenuItem();
            menuItem = menuItem == null ? "" : menuItem.replaceAll("[^\\d]", "");
            if (menuItem.length() > 0) {
                int menuNumber = Integer.parseInt(menuItem);
                if (menuNumber > methodArray.length - 1) {
                    System.out.println("Такого номера нет");
                    System.exit(0);
                }
                testLineParsing(testClass, methodArray[menuNumber].getName());
            } else {
                System.out.println("Укажи номер");
                System.exit(0);
            }
        } else {
            System.out.println("В классе " + testClass.getSimpleName() + ", методы не обнаружены");
            startTest();
        }
    }

    private void testLineParsing(Class testClass, String methodName) {
        //System.out.println("--- " + testClass.getSimpleName() + " ---------------------------------------------");
        switch (methodName) {
            case "getNumberFromRightPart":
            case "getNumberFromLeftPart":
            case "findFirstMathSymbol":
                testLineClassesByStringResult(testClass, methodName);
                break;
            case "isFinalNumber":
                testLineClassesByBooleanResult(testClass, methodName);
                break;
            case "getDouble":
            case "getInteger":
                System.out.println("Не подлежит тестированию");
                break;
            default:
                System.out.println("Метод не обнаружен");
        }
    }

    private void testStringUtilsClass(Class testClass) {
        Method[] methodArray = testClass.getDeclaredMethods();
        Arrays.sort(methodArray, Comparator.comparing(Method::getName));
        if (methodArray.length > 0) {
            System.out.println("--- Выбери метод ---------------------------------------------");
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(i + " - " + methodArray[i].getName());
            }
            System.out.print("твой выбор: ");
            String menuItem = getMenuItem();
            menuItem = menuItem == null ? "" : menuItem.replaceAll("[^\\d]", "");
            if (menuItem.length() > 0) {
                int menuNumber = Integer.parseInt(menuItem);
                if (menuNumber > methodArray.length - 1) {
                    System.out.println("Такого номера нет");
                    System.exit(0);
                }
                testLineClassesByStringResult(testClass, methodArray[menuNumber].getName());
            } else {
                System.out.println("Укажи номер");
                System.exit(0);
            }
        } else {
            System.out.println("В классе " + testClass.getSimpleName() + ", методы не обнаружены");
            startTest();
        }
    }

    private void testLineClassesByBooleanResult(Class testClass, String methodName) {
        System.out.println("--- " + testClass.getSimpleName() + " ---------------------------------------------");
        for (TestParameter testParameter : TEST_PARAMETER_LIST.stream().filter(item -> item.getClassName().equals(testClass.getSimpleName())).filter(item -> item.getMethodName().equals(methodName)).collect(Collectors.toList())) {
            Boolean result = (Boolean) testMethod(testClass, testParameter.getMethodName(), testParameter.getMethodValueArray());
            System.out.println(
                    result == null ?
                            ("ПРОВАЛ: Метод: " + testParameter.getMethodName() + ", отправлено: '" + testParameter.getValue() + "', получено: 'null'") :
                            ((testParameter.getResult().equals(result) ? "УСПЕХ : " : "ПРОВАЛ: ") + "Метод: " + testParameter.getMethodName() + ", отправлено: '" + testParameter.getValue() + "', получено: '" + result + "', ожидаем: '" + testParameter.getResult() + "'")
            );
        }
    }

    private void testLineClassesByStringResult(Class testClass, String methodName) {
        System.out.println("--- " + testClass.getSimpleName() + " ---------------------------------------------");
        for (TestParameter testParameter : TEST_PARAMETER_LIST.stream().filter(item -> item.getClassName().equals(testClass.getSimpleName())).filter(item -> item.getMethodName().equals(methodName)).collect(Collectors.toList())) {
            String result = (String) testMethod(testClass, testParameter.getMethodName(), testParameter.getMethodValueArray());
            System.out.println(
                    result == null ?
                            ("ПРОВАЛ: Метод: " + testParameter.getMethodName() + ", отправлено: '" + testParameter.getValue() + "', получено: 'null'") :
                            ((testParameter.getResult().equals(result) ? "УСПЕХ : " : "ПРОВАЛ: ") + "Метод: " + testParameter.getMethodName() + ", отправлено: '" + testParameter.getValue() + "', получено: '" + result + "', ожидаем: '" + testParameter.getResult() + "'")
            );
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private Object testMethod(Class testClass, String methodName, Object[] parameterValueArray) {
        try {
            Method[] methodArray = testClass.getDeclaredMethods();
            for (Method item : methodArray) {
                Class[] parameterTypeArray = item.getParameterTypes();
                if (item.getName().equals(methodName) && parameterTypeArray.length == parameterValueArray.length) {
                    Method method = testClass.getDeclaredMethod(methodName, parameterTypeArray);
                    method.setAccessible(true);
                    return method.invoke(testClass, parameterValueArray);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
//            System.out.println("---------------------------------------------------------------");
//            System.out.println(testClass.getSimpleName() + ", " + methodName + ": " + ex.getMessage());
//            System.out.println("---------------------------------------------------------------");
            //exception.printStackTrace();
        }
        return null;
    }

    private Object testMethod(Class testClass, String methodName, Class[] parameterTypeArray, Object[] parameterValueArray) {
        try {
            Method[] methodArray = testClass.getDeclaredMethods();
            for (Method item : methodArray) {
                if (item.getName().equals(methodName) && parameterTypeArray.length == parameterValueArray.length) {
                    Method method = testClass.getDeclaredMethod(methodName, parameterTypeArray);
                    method.setAccessible(true);
                    return method.invoke(testClass, parameterValueArray);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
//            System.out.println("---------------------------------------------------------------");
//            System.out.println(testClass.getSimpleName() + ", " + methodName + ": " + ex.getMessage());
//            System.out.println("---------------------------------------------------------------");
            //exception.printStackTrace();
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------

    private static class TestParameter<R> {

        private String className;
        private String methodName;
        private Class[] methodArgumentArray;
        private Object[] methodValueArray;
        private String value;
        private R result;

        public TestParameter() {
        }

        public TestParameter(String className, String methodName, Class[] methodArgumentArray, Object[] methodValueArray, String value, R result) {
            this.className = className;
            this.methodName = methodName;
            this.methodArgumentArray = methodArgumentArray;
            this.methodValueArray = methodValueArray;
            this.value = value;
            this.result = result;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public Class[] getMethodArgumentArray() {
            return methodArgumentArray;
        }

        public void setMethodArgumentArray(Class[] methodArgumentArray) {
            this.methodArgumentArray = methodArgumentArray;
        }

        public Object[] getMethodValueArray() {
            return methodValueArray;
        }

        public void setMethodValueArray(Object[] methodValueArray) {
            this.methodValueArray = methodValueArray;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public R getResult() {
            return result;
        }

        public void setResult(R result) {
            this.result = result;
        }
    }

    private static class All {
    }
}
