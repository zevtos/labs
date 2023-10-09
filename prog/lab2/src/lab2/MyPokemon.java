package lab2;

import java.util.Scanner;

import lab2.pokemons.*;
import ru.ifmo.se.pokemon.*;
import lab2.moves.special.*;
import lab2.moves.status.*;

import java.util.*;

public class MyPokemon extends Pokemon {
    public MyPokemon(String name,
                     int level,
                     int hp,
                     int attack,
                     int defense,
                     int specialAttack,
                     int specialDefense,
                     int speed, Move[] attacks) {
        super(name, level);
        setStats(hp, attack, defense, specialAttack, specialDefense, speed);
        for (Move x : attacks) {
            addMove(x);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Battle battle = new Battle();

        HashMap<String, Move> attacks = new HashMap<>();
        attacks.put("Blizzard", new BlizzardAttack());
        attacks.put("Eruption", new EruptionAttack());
        attacks.put("HydroPump", new HydroPumpAttack());
        attacks.put("IceBeam", new IceBeamAttack());
        attacks.put("PsyBeam", new PsybeamAttack());
        attacks.put("Thunderbolt", new ThunderboltAttack());
        attacks.put("ThunderShock", new ThunderShockAttack());
        attacks.put("LightScreen", new LightScreenAttack());
        attacks.put("ThunderWave", new ThunderWaveAttack());
        attacks.put("Trick", new TrickAttack());

        HashMap<String, Pokemon> pokemons = new HashMap<>();
        pokemons.put("Hoppip", new Hoppip("Чужой", 1));
        pokemons.put("Electrode", new Electrode("Хищник", 1));
        pokemons.put("Shroomish", new Shroomish("Грибок", 1));
        pokemons.put("Purrloin", new Purrloin("Скрыватель", 1));
        pokemons.put("Breloom", new Breloom("Брелум", 1));
        pokemons.put("Voltorb", new Voltorb("Вольт", 1));


        System.out.println("MyPokemon program");
        int number;
        do {
            System.out.println("Введите способ запуска программы из предложенных");
            System.out.println("0 - тестовый запуск");
            System.out.println("1 - ручной ввод покемонов и ручное добавление атак");
            System.out.println("2 - выбор покемонов из уже существующих");
            System.out.println("3 - выбор покемонов + создание");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                break;
            } else {
                System.out.println("Вы ввели не число. Пожалуйста, попробуйте снова.");
                scanner.nextLine(); // Сбрасываем буфер ввода
            }
        } while (true);
        int number_Ally = 0;
        int number_Foe = 0;
        Pokemon[] Ally;
        Pokemon[] Foe;
        if(number == 1 || number == 2 || number == 3)
        {
            System.out.println("Введите количество покемонов в команде Ally:");
            number_Ally = integer_request(scanner, 1, 3);
            System.out.println("Введите количество покемонов в команде Foe:");
            number_Foe = integer_request(scanner, 1, 3);
            Ally = new Pokemon[number_Ally];
            Foe = new Pokemon[number_Foe];
        }else{
            number_Ally = 3;
            number_Foe = 3;
            Ally = new Pokemon[3];
            Foe = new Pokemon[3];
        }
        switch (number) {
            case 1:
                System.out.println("Ручное создание покемонов и добавление к ним существующих атак");
                for (Pokemon x : create_pokemons(scanner, number_Ally, attacks)) {
                    battle.addAlly(x);
                }
                for (Pokemon x : create_pokemons(scanner, number_Foe, attacks)) {
                    battle.addFoe(x);
                }
                break;
            case 2:
                System.out.println("Доступные покемоны для выбора:");
                System.out.println("Hoppip, Electrode, Shroomish, Purrloin, Breloom, Voltorb");
                for (Pokemon x : pokemons_request(scanner, number_Ally, pokemons, "Ally")) {
                    battle.addAlly(x);
                }
                for (Pokemon x : pokemons_request(scanner, number_Foe, pokemons, "Foe")) {
                    battle.addFoe(x);
                }
                break;
            case 3:
                System.out.println("Доступные покемоны для выбора:");
                System.out.println("Hoppip, Electrode, Shroomish, Purrloin, Breloom, Voltorb");
                System.out.println("Введите количество покемонов которые будут созданы вручную для команды Ally");
                int number_create_Ally = integer_request(scanner, 0, number_Ally);
                System.out.println("Введите количество покемонов которые будут созданы вручную для команды Foe");
                int number_create_Foe = integer_request(scanner, 0, number_Foe);

                for (Pokemon x : pokemons_request(scanner, number_Ally - number_create_Ally, pokemons, "Ally")) {
                    battle.addAlly(x);
                }
                for (Pokemon x : pokemons_request(scanner, number_Foe - number_create_Foe, pokemons, "Foe")) {
                    battle.addFoe(x);
                }
                System.out.println("Создание покемонов в команду Ally");
                for (Pokemon x : create_pokemons(scanner, number_create_Ally, attacks)) {
                    battle.addAlly(x);
                }
                System.out.println("Создание покемонов в команду Foe");
                for (Pokemon x : create_pokemons(scanner, number_create_Foe, attacks)) {
                    battle.addFoe(x);
                }

                break;
            default:
                System.out.println("Тестовый запуск:");

                int i = 0;
                for (Pokemon value : pokemons.values()) {
                    if (i < 3) {
                        Ally[i] = value;
                    } else {
                        Foe[i % 3] = value;
                    }
                    i++;
                }
                for (Pokemon al : Ally) {
                    battle.addAlly(al);
                }
                for (Pokemon fo : Foe) {
                    battle.addFoe(fo);
                }
        }

        // Запускаем бой
        battle.go();

    }

    // создание number покемонов и добвление им атак
    public static MyPokemon[] create_pokemons(Scanner scanner, int number, HashMap<String, Move> attacks) {
        MyPokemon[] pokemonArray = new MyPokemon[number];
        for (int i = 0; i < number; i++) {
            System.out.println("Введите через пробел название, уровень и характеристики: HP, attack, defense, special attack, special defense, speed: ");
            scanner.nextLine();
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");
            if (inputParts.length != 8) {
                System.out.println("Неправильный формат ввода. Пожалуйста, введите все характеристики.");
                i--; // Чтобы повторно запросить характеристики для текущего индекса массива
                continue;
            }
            System.out.println("Укажите количество атак:");
            int number_attacks = integer_request(scanner, 0, 4);
            System.out.println("Укажите атаки покемона из доступных:");
            Move[] add_attacks = add_attacks(scanner, number_attacks, attacks);
            try {
                String name = inputParts[0];
                int level = Integer.parseInt(inputParts[1]);
                int hp = Integer.parseInt(inputParts[2]);
                int attack = Integer.parseInt(inputParts[3]);
                int defense = Integer.parseInt(inputParts[4]);
                int specialAttack = Integer.parseInt(inputParts[5]);
                int specialDefense = Integer.parseInt(inputParts[6]);
                int speed = Integer.parseInt(inputParts[7]);

                MyPokemon pokemon = new MyPokemon(name, level, hp, attack, defense, specialAttack, specialDefense, speed, add_attacks);
                pokemonArray[i] = pokemon; // Добавляем созданный покемон в массив
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат чисел. Пожалуйста, введите числовые значения.");
                i--; // Чтобы повторно запросить характеристики для текущего индекса массива
            }
        }
        return pokemonArray;
    }

    // создание массива атак(пользователь выбирает number атак)
    public static Move[] add_attacks(Scanner scanner, int number, HashMap<String, Move> attacks) {
        Move[] to_return = new Move[number];
        int count = 0;
        while (count < number) {
            System.out.println("Blizzard, Eruption, HedroPump, IceBeam, Psybeam, Thunderbolt, ThunderShock, LightScreen, ThunderWave, Trick");
            System.out.println("Введите атаку из предложенных выше");
            boolean flag = true;
            String input;
            scanner.nextLine();
            do {
                input = scanner.next();
                switch (input) {
                    case "Blizzard", "Eruption", "HydroPump", "IceBeam", "Psybeam", "Thunderbolt", "ThunderShock", "LightScreen", "ThunderWave", "Trick":
                        flag = false;
                        break;
                    default:
                }
                if (flag) {
                    System.out.println("Неправильный формат или такая атака не создана");
                    System.out.println("Доступные атаки:");
                    System.out.println("Blizzard, Eruption, HedroPump, IceBeam, Psybeam, Thunderbolt, ThunderShock, LightScreen, ThunderWave, Trick");
                }
            } while (flag);
            to_return[count] = attacks.get(input);

            count++;
        }
        return to_return;
    }

    // запрос пользователя на выбор number существующих покемонов в команду team
    public static Pokemon[] pokemons_request(Scanner scanner, int number, HashMap<String, Pokemon> pokemons, String team) {
        int count = 0;
        Pokemon[] to_return = new Pokemon[number];
        while (count < number) {
            System.out.println("Введите, соблюдая регистр, название покемона из предложенных ранее для добавления в команду " + team + " или help(для отображения существующих покемонов):");
            boolean flag = true;
            String input;
            scanner.nextLine();
            do {
                input = scanner.next();
                switch (input) {
                    case "Hoppip", "Electrode", "Shroomish", "Purrloin", "Breloom", "Voltorb":
                        flag = false;
                        break;
                    default:

                }
                if (flag) {
                    if(!input.equals("help")) {
                        System.out.println("Неправильный формат ввода или такой покемон не был создан, доступные покемоны:");
                    }
                    System.out.println("Hoppip, Electrode, Shroomish, Purrloin, Breloom, Voltorb");
                }
            } while (flag);
            to_return[count] = pokemons.get(input);

            count++;
        }
        return to_return;
    }

    // запрос пользователя на ввод числа в диапазоне от minValue до maxValue
    public static int integer_request(Scanner scanner, int minValue, int maxValue) {
        int number;
        do {
            System.out.print("Введите число от " + minValue + " до " + maxValue + ": ");
            scanner.nextLine(); // Сбрасываем буфер ввода
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number >= minValue && number <= maxValue) {
                    break; // Если введено корректное число в диапазоне, выходим из цикла
                } else {
                    System.out.println("Число должно быть в диапазоне от " + minValue + " до " + maxValue);
                }
            } else {
                System.out.println("Вы ввели не число. Пожалуйста, попробуйте снова.");

            }
        } while (true);

        return number;
    }

    // возвращает bollean сработал шанс или нет
    public static boolean chance(double c) {
        return (c / 100) > Math.random();
    }
}