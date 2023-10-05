import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {
    static BufferedImage image;
    static int height, width;
    static float size = 30;
    static String path = "test";
    static int posY = 2, posX = 2;
    static  Color color = Color.WHITE;

    static int font = Font.PLAIN;
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("Здравствуйте! Для получения мануала добавьте help к " +
                    "введенному ранее");
            return;
        }
        switch (args[0]) {
            case "help": help(); break;
            case "mem": mem(args); break;
            default: System.out.println("Неверная функция");
        }
    }
    public static void mem(String[] args) {
        if (args.length < 3) {
            System.out.println("Недостаточно аргументов! Для справки используйте help");
            return;
        }

        try {
            image = ImageIO.read(new File(args[1]));
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException e) {
            System.out.println("Ошибка открытия картинки");
            return;
        }

        Graphics imgStr = image.getGraphics();

        for(int i = 3; i < args.length; i = i + 1) {
            if (Objects.equals(args[i], "locationY")){
                i++;
                if (!Objects.equals(args[i], "="))
                {
                    System.out.println("Ошибка ввода аргумента " + args[i-1]);
                    continue;
                }
                i++;
                if (args.length == i){
                    System.out.println("Ошибка ввода аргумента " + args[i-2]);
                    break;
                }
                switch (args[i]) {
                    case "TOP" -> posY = 1;
                    case "CENTER" -> posY = 2;
                    case "BOTTOM" -> posY = 3;
                    default -> {
                        System.out.println("Ошибка ввода аргумента " + args[i - 2]);
                        i--;
                    }
                }
            }
            if (Objects.equals(args[i], "locationX")){
                i++;
                if (!Objects.equals(args[i], "="))
                {
                    System.out.println("Ошибка ввода аргумента " + args[i-1]);
                    continue;
                }
                i++;
                if (args.length == i){
                    System.out.println("Ошибка ввода аргумента " + args[i-2]);
                    break;
                }
                switch (args[i]) {
                    case "LEFT" -> posX = 1;
                    case "CENTER" -> posX = 2;
                    case "RIGHT" -> posX = 3;
                    default -> {
                        System.out.println("Ошибка ввода аргумента " + args[i - 2]);
                        i--;
                    }
                }
            }
            else if (Objects.equals(args[i], "size")){
                i++;
                if (!Objects.equals(args[i], "="))
                {
                    System.out.println("Ошибка ввода аргумента " + args[i-1]);
                    continue;
                }
                i++;
                if (args.length == i ){
                    System.out.println("Ошибка ввода аргумента " + args[i-2]);
                    break;
                }
                try {
                    size = Float.parseFloat(args[i]);
                }
                catch (NumberFormatException e){
                    System.out.println("Ошибка ввода аргумента " + args[i-2]);
                    i--;
                }
            }
            else if (Objects.equals(args[i], "font")){
                i++;
                if (!Objects.equals(args[i], "="))
                {
                    System.out.println("Ошибка ввода аргумента " + args[i-1]);
                    continue;
                }
                i++;
                if (args.length == i){
                    System.out.println("Ошибка ввода аргумента " + args[i-2]);
                    break;
                }
                switch (args[i]) {
                    case "BOLD" -> font = Font.BOLD;
                    case "PLAIN" -> font = Font.PLAIN;
                    case "ITALIC" -> font = Font.ITALIC;
                    case "ROMAN_BASELINE" -> font = Font.ROMAN_BASELINE;
                    default -> {
                        System.out.println("Ошибка ввода аргумента " + args[i - 2]);
                        i--;
                    }
                }

            }
            else if (Objects.equals(args[i], "color")){
                i++;
                if (!Objects.equals(args[i], "="))
                {
                    System.out.println("Ошибка ввода аргумента " + args[i-1]);
                    continue;
                }
                i++;
                if (args.length == i){
                    System.out.println("Ошибка ввода аргумента " + args[i-2]);
                    break;
                }
                switch (args[i]) {
                    case "BLACK" -> color = Color.BLACK;
                    case "WHITE" -> color = Color.WHITE;
                    case "RED" -> color = Color.RED;
                    case "YELLOW" -> color = Color.YELLOW;
                    default -> {
                        System.out.println("Ошибка ввода аргумента " + args[i - 2]);
                        i--;
                    }
                }

            }
            else if (Objects.equals(args[i], "save")){
                i++;
                if (!Objects.equals(args[i], "="))
                {
                    System.out.println("Ошибка ввода аргумента " + args[i-1]);
                    continue;
                }
                i++;
                if (args.length == i){
                    System.out.println("Ошибка ввода аргумента " + args[i-2]);
                    break;
                }
                switch (args[i]) {
                    case "OVERWRITE" -> path = args[1];
                    case "NEW" -> {
                        i++;
                        if (args.length == i) {
                            System.out.println("Ошибка ввода аргумента " + args[i - 2]);
                            break;
                        }
                        path = args[i] + ".png";
                    }
                    default -> {
                        System.out.println("Ошибка ввода аргумента " + args[i - 2]);
                        i--;
                    }
                }
            }
        }
        imgStr.setFont(imgStr.getFont().deriveFont(font, size));
        imgStr.setColor(color);
        imgStr.drawString(args[2], width*posX/4, height*posY/4);
        imgStr.dispose();

        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            System.out.println("Ошибка сохранения картинки");
            return;
        }

    }

    public static void help() {
        System.out.println("###################################");
        System.out.println("Существует функция mem:");
        System.out.println("  Обязательный ввод:");
        System.out.println("    1) Путь к файлу");
        System.out.println("    2) Текст надписи\n");
        System.out.println("  Необязательный ввод: пишется после основных по такому принципу - свойство = значение");
        System.out.println("    1) locationY - Расположение надписи по высоте");
        System.out.println("        1.1) TOP - Расположение надписи наверху");
        System.out.println("        1.2) CENTER - Расположение надписи по центру(по умолчанию)");
        System.out.println("        1.3) BOTTOM - Расположение надписи снизу");
        System.out.println("    2) locationX - Расположение надписи по горизонтали");
        System.out.println("        1.1) LEFT - Расположение надписи слева");
        System.out.println("        1.2) CENTER - Расположение надписи по центру(по умолчанию)");
        System.out.println("        1.3) RIGHT - Расположение надписи справа");
        System.out.println("    3) size - Размер текста, ввод - число(по умолчанию = 30)");
        System.out.println("    4) font - Шрифт текста");
        System.out.println("        1.1) BOLD - Жирный");
        System.out.println("        1.2) PLAIN - Обычный(по умолчанию)");
        System.out.println("        1.3) ITALIC - Курсив");
        System.out.println("        1.3) ROMAN_BASELINE - Roman Baseline");
        System.out.println("    5) color - Цвет текста");
        System.out.println("        1.1) BLACK - Черный");
        System.out.println("        1.2) WHITE - Белый(по умолчанию)");
        System.out.println("        1.3) RED - Красный");
        System.out.println("        1.3) YELLOW - Желтый");
        System.out.println("    6) save - Тип сохранения");
        System.out.println("        1.1) OVERWRITE - Перезаписывает в исходный файл");
        System.out.println("        1.2) NEW НАЗВАНИЕ ФАЙЛА - Записывает в указанный файл (нужный файл " +
                "вместо НАЗВАНИЕ ФАЙЛА, разрешение указывать не нужно(по умолчанию test)\n");
        System.out.println("  Пример:");
        System.out.println("    java -jar myapp.jar mem C:\\7B_ZSTleM78.jpg Hello size = 20 font = ITALIC color = YELLOW save = NEW test2.smthWrong locationY = TOP locationX = RIGHT");
    }
}