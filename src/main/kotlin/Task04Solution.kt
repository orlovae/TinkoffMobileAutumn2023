/**
На диком западе хотяд купюры номиналами a1, a2, ..., an, долларов. Однажды ковбой Джо решил ограбить банк. Он выбрал
очень неудачный момент для ограбления, вед сейчас в банке находятся ровнопо две купюры каждого существующего
номинала.
Ковбой Джо хочет украть ровно n долларов, ни долларом больше, ни долларом меньше. Помогите ему или сообщите, что его
план неосуществим.
Формат входных данных
В первой строке даны целые числа n, m (1 <= n <= 10e9, 1 <= m <= 10) - необходимая ковбою Джо сумма и количество
номиналов купюр.
Во второй троке вводятся m попарно различных целых чисел a1, a2, ... an (1 <= ai <=10e9) - существующие номиналы
купюр.
Формат выходных данных
Если ковбой Джо сможет украсть ровно n долларов, выведите число k - количество украденных купюр. Затем выведите k
целых чисел - номиналы купюр. Если решений несколько, вы можете вывести любое.
В противном случае выводите -1
Примеры данных
Пример 1
Ввод
5 2
1 2
Вывод
3
1 2 2
Пример 2
Ввод
7 2
1 2
Вывод
-1
Пример 3
Ввод
5 2
3 4
Вывод
-1
 Решение. Сначала делаем проверку, если минимальный номинал умноженный на количество номиналов больше чем сумма
 нужная Джо, то решения не будет.
 Далее вычитаем из суммы первый номинал и сравниваем с нужной суммой:
 если меньше нуля, то прибавляем к сумме этот же номинал, выходим из цикла и переходим к следующему номиналу
 если больше нуля, то добавляем в ответ номинал, и переходим к такому же номиналу.
 если равно нулю, то это ответ, записываем в ответ номинал, форматируем ответ и выходим из всех циклов.
 Печать ответа.
 */
class Task04Solution {
    private val DJO = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
    private val sum = DJO[0]
    private val count = DJO[1]
    private val list = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }

    init {
        var answer = "-1"

        if (list.first() * count <= sum) {
            var sumTmp = sum
            val seq = mutableListOf<Int>()
            run loop@{
                for (j in list.lastIndex downTo 0) {
                    for (i in 1..count) {
                        sumTmp -= list[j]
                        when {
                            sumTmp < 0 -> {
                                sumTmp += list[j]
                                break
                            }

                            sumTmp > 0 -> {
                                seq.add(list[j])
                            }

                            sumTmp == 0 -> {
                                seq.add(list[j])
                                answer = "${seq.size}\n${seq.joinToString(" ")}"
                                return@loop
                            }
                        }
                    }
                }
            }
        }
        println(answer)
    }
}