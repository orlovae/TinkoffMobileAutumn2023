/**
Однажды ковбой Джо решил обзавестись револьвером и пришёл в оружейный механизм. У ковбоя s долларов, а на выбор
 представлены n револьверов с ценами a1, a2, ..., an.
 Помогите Джо выбрать самый дорогой револьвер, который он может себе позволить или сообщите, что такого не
 существует.
 Формат входных данных
 В первой строке даны целые числа n, s (1 <= n <= 2x10e5, 1 <= s <= 10e9) - количество револьверов в магазине и
 количестве долларов у ковбоя Джо.
 Во второй строке даны n целых чисел a1, a2, ..., an (1 <= ai <= 10e9) - цены револьверов в магазине.
 Формат выходных данных
 Выведите единственное число - цену самого дорого револьвера, который ковбой Джо сможет себе позволить, если такого
 револьвера нет, выведите 0.
 Примеры данных
 Пример 1
 Ввод
 6 13
 3 10 300 15 3
 Вывод
 10
 Пример 2
 Ввод
 3 4
 5 12 10
 Вывод
 0
 */
class Task01Solution {
    private val line1 = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
    private val money = line1[1]
    private val listPrice = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

    init {
        val answerList = listPrice.filter { it <= money }
        println(
            if (answerList.isNotEmpty()) {
                answerList.max()
            } else {
                0
            }
        )
    }
}