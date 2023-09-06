/**
Перед ковбоем Джо выложены n карт со значениями a1, a2, ..., an Он хочет получить выигрышную последовательность карт
со значениями b1, b2, ... bn.
Ковбой может выбрать непрерывный отрезок карт в своей последовательности [l, r] (1 <= l <= r <= n) и упорядочить
карты в этом отрезке по неубыванию. Например, если перед ковбоем лежать карты {3, 3, 2, 5, 1, 5}, он может выбрать
отрезок [2, 5] и получить последовательность {3, 1 , 2 ,3, 5, 5}.
Получится ли у ковбоя Джо получить выигрышную последовательность с помощью применения вышеописанной операции ровно
один раз?
Формат входных данных
В первой строке дано целое число n (1 <= n <= 2x10e5) - количество карт в последовательности. Во второй строке даны
n целых чисел a1, a2, ..., an (1 <= ai <= 10e9) - последовательность ковбоя Джо
В третьей строке даны n целых чисел b1, b2, ... bn (1 <= bi <= 10e9) - выигрышная последовательность.
Формат выходных данных
Выведите число "YES" (без кавычек), если Джо может получить выигрышную последовательность, иначе выведите "NO" .
Примеры данных
Пример 1
Ввод
5
1 4 2 2 4
1 4 4 2 2
Вывод
NO
Пример 2
Ввод
6
5 1 2 5 3 5
5 1 2 3 5 5
Вывод
YES
Пример 3
3
4 1 2
1 4 7
Вывод
NO
Пример 4
1
7
7
Вывод
YES
Пример 5
7
4 4 1 7 5 3 8
4 1 4 5 7 3 8
Вывод
YES

Решение:
 Два указателя сверху и снизу запускаем для правого конца и левого, пока не будет первое расхождение в символах.
 Далее по полученным индексам выбираем массив карт Джо, сортируем его по возрастанию.
 Далее по полученным индексам выбираем массив выигрышных карт
 Далее по полученным сабмассивам делаем проверку двумя указателями сверху и снизу для правого конца и левого. При
 первом расхождении выходим из цикла и печатаем ответ.
 Если цикл полностью пройдет, то ответ положительный.

 */
class Task03Solution {
    private val countCard = readln().trim().toInt()
    private val DJO = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()
    private val win = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

    init {
        var isSolved = true
        var leftTop = 0
        var leftBottom = 0
        var rightTop = countCard - 1
        var rightBottom = countCard - 1


        while (DJO[leftTop] == win[leftBottom] && leftTop < countCard - 1) {
            leftTop += 1
            leftBottom += 1
        }
        while (DJO[rightTop] == win[rightBottom] && rightTop > 0) {
            rightTop -= 1
            rightBottom -= 1
        }


        val subDJO = DJO.subList(leftTop, rightTop + 1).sorted()
        val subWin = win.subList(leftTop, rightTop + 1)
        var leftTopSub = 0
        var leftBottomSub = 0
        var rightTopSub = subDJO.lastIndex
        var rightBottomSub = subWin.lastIndex


        while (leftTopSub <= rightTopSub) {
            if (subDJO[leftTopSub] == subWin[leftBottomSub]) {
                leftTopSub += 1
                leftBottomSub += 1
            } else {
                isSolved = false
                break
            }
            if (subDJO[rightTopSub] == subWin[rightBottomSub]) {
                rightTopSub -= 1
                rightBottomSub -= 1
            } else {
                isSolved = false
                break
            }
        }


        println(
            if (isSolved) "YES" else "NO"
        )
    }
}