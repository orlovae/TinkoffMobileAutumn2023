import kotlin.math.min

/**
Однажды ковбой Джо нанялся в помощники шерифу. Шериф выдал ковбою Джо строку s и попросил собрать из неё букв как
можно больше слов sheriff. Каждая буква может использоваться не более чем в одном слове.
Квобой Джо тут же приступил к заданию шерифа, но к сожалению, он не умеет читать. Помогите ковбою Джо.
Формат входных данных
Дана строка s(1<=|s|<=2x10e5) состоящая из маленьких букв латинского алфавита
Формат выходных данных
Выводите максимальное количество слов sheriff которое можно собрать из букв строки s
Примеры данных
Пример 1
Ввод
fheriherffazfszkisrrs
Вывод
2
Пример 2
Ввод
rifftratatashe
Вывод
1
Пример 3
Ввод
thegorillaiswatching
Вывод
0

Решение:
1. Заносим исходную строку в словарь, где символ - ключ, а значение - количество повторений символа в строке.
2. Так как у нас в слове sheriff две буквы f, то модифицируем её значение в словаре разделив на 2.
3. Идём циклом по слову sheriff и в изменяемую переменную кладём минимальное значение повторений символа в
исходной строке. Если хоть один символ из слова sheriff не встречается, сразу выходим из цикла и печатем 0
4. После прохода по циклу печатаем ответ.

 */
class Task02Solution {
    private val inputString = readln().trim()
    private val sheriff = "sheriff".toCharArray()

    init {
        val map = mutableMapOf<Char, Int>()
        inputString.forEach {
            if (map.containsKey(it)) {
                map[it] = map[it]!! + 1
            } else {
                map[it] = 1
            }
        }

        if (map.containsKey('f')) {
            val countF = map['f']!! / 2
            map['f'] = countF
        }

        var count = Int.MAX_VALUE
        run loop@{
            sheriff.forEach {
                if (map.containsKey(it)) {
                    count = min(count, map[it]!!)
                } else {
                    count = 0
                    return@loop
                }
            }
        }
        println(count)
    }
}