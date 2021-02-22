package com.example.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;

@Controller
public class Calculator {
    @GetMapping("/")
    public String index() {
        return "redirect:/app";
    }

    @GetMapping("/app")
    public String welcome() {
        return "calculator";
    }

    @GetMapping("/app/calculate")
    @ResponseBody
    public String calculateResult(@RequestParam String input) {
        LinkedList<String> data = new LinkedList<>();
        int begin = 0;
        input = input.trim();
        if (input.length() == 0) {
            return "";
        }

        // Omit, if first character is an operator or percent
        if (isOperator(input.charAt(0)) || input.charAt(0) == 'z') {
            input = input.substring(1);
        }
        if (input.length() == 0) {
            return "0";
        }

        // Omit, if last character is an operator
        if (isOperator(input.charAt(input.length() - 1))) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.length() == 0) {
            return "0";
        }

        for (int pos = 0; pos < input.length(); pos++) {
            if (isOperator(input.charAt(pos)) || input.charAt(pos) == 'z') {
                if (begin != pos) {
                    String val = input.substring(begin, pos);
                    data.add(val);
                }
                String op = input.substring(pos, pos + 1);
                data.add(op);
                begin = pos + 1;
            } else if (pos == input.length() - 1) {
                String val = input.substring(begin, pos + 1);
                data.add(val);
            }
        }
//        printData(data);
        while (hasOperator(data) || hasPercentage(data)) {
            // Calculate all percentages
            while (findOperator(data, "z", "z") > 0) {
                int pos = findOperator(data, "z", "z");
                String result = calculate(data.get(pos - 1), "0", data.get(pos));
                data.remove(pos);
                data.remove(pos - 1);
                data.add(pos - 1, result);
//                printData(data);
            }

            // Calculate all division and multiplication
            while (findOperator(data, "m", "d") > 0) {
                int pos = findOperator(data, "m", "d");
                String result = calculate(data.get(pos - 1), data.get(pos + 1), data.get(pos));
                data.remove(pos + 1);
                data.remove(pos);
                data.remove(pos - 1);
                data.add(pos - 1, result);
//                printData(data);
            }

            // Calculate all addition and subtraction
            while (findOperator(data, "a", "s") > 0) {
                int pos = findOperator(data, "a", "s");
                String result = calculate(data.get(pos - 1), data.get(pos + 1), data.get(pos));
                data.remove(pos + 1);
                data.remove(pos);
                data.remove(pos - 1);
                data.add(pos - 1, result);
//                printData(data);
            }
        }

        return data.get(0);
    }

    private String calculate(String a, String b, String operator) {
        Double x = Double.valueOf(a);
        Double y = Double.valueOf(b);
        Double xy;
        if (operator.equals("z")) {
            xy = x / 100;
        } else if (operator.equals("m")) {
            xy = x * y;
        } else if (operator.equals("d")) {
            xy = x / y;
        } else if (operator.equals("a")) {
            xy = x + y;
        } else {
            xy = x - y;
        }
        return xy.toString();
    }

    private int findOperator(LinkedList<String> data, String operator1, String operator2) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(operator1) || data.get(i).equals(operator2)) {
                return i;
            }
        }
        return -1;
    }


    private boolean hasOperator(LinkedList<String> data) {
        for (String str : data) {
            if (isOperator(str.charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPercentage(LinkedList<String> data) {
        for (String str : data) {
            if (str.charAt(0) == 'z') {
                return true;
            }
        }
        return false;
    }

    private void printData(LinkedList<String> data) {
        System.out.println(data);
    }

    private boolean isOperator(char ch) {
        return (ch == 'a' || ch == 's' || ch == 'm' || ch == 'd');
    }
}
