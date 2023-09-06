/**
Однажды ковбой Джо забрёл в жуткую заброшенную шахту, в кторой обитают n  духов с номерами 1, 2, ..., n. Сейчас
каждый дух состоит в банде из самого себя. В один момент времени каждый дух может находиться вовнов одной банде. По
одиночке духи слабы, поэтому вскоре банды начнут объединяться. Когда две банды объединаяются, создаётся новая банда,
в которую попадают все духи из объединающихся банд, в то время как старные банды распадаются.
Ковбоя Джо оглушил душераздирающий крик, который сообщил ему о необходимости ответить на m вопросов. Если ковбой Джо
откажетя ответчать на вопросы крика или ошибётся, то навечно сгинет в заброшенной шахте.
Дух задаст m вопросов, каждый из которых относится к одному из трёх типов.
1. Духи x и y объединяются в банду. Если они уже находятся в одной банде, ничего не происходит.
2. Крик спрашивает ковбоя Джо, находятся ли духи x и y в одной банде.
3. Крик спрашивает ковбоя Джо, в скольких бандах побывал дух x.
Например, если n = 7, а банды выглядели так: [1,3], [4,6,2], [5], [7] и крик сообщил об объединении банд с духами 1
и 5, банды будут выглядеть так: [1, 5, 3], [4, 6, 2], [7]. Духи 1, 3, 5 побывали в двух бандах, духи 2, 4, 6, 7 в
одной.
Формат входных данных
В первой строке даны целые числа n, m (1 <= n, m <= 2 x 10e5) - количество духов и вопросов крика.
Следующие m строк содержат описание вопросов:
1 x y (1<= x, y <= n)
2 x y (1<= x, y <= n)
3 x  (1<= x <= n)
Формат выходных данных
Для каждого вопроса вторго типа выведите "YES" или "NO" (без кавычек).
Для каждого вопроса третьего типа выведите целое число - ответ на вопрос.
Примеры данных
Ввод
7 13
2 3 1
3 3
1 2 4
2 1 1
3 4
2 3 4
1 3 4
3 4
1 7 3
1 1 3
3 7
3 1
2 7 4
Вывод
NO
1
YES
2
NO
3
3
2
YES
 */
class Task06 {
    private val firstLine = readln().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
    private val countGhost = firstLine.first()
    private val countIssue = firstLine.last()
    private val ghostList = getGhostList(countGhost)
    private val othersLine = getOthersLine(countIssue)

    init {
        othersLine.forEach {string ->
            val line = string.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
            val type = line[0]
            when (type) {
                1 -> mergeGHost(
                    indexOneGhost = line[1] - 1,
                    indexTwoGhost = line[2] - 1
                )

                2 -> println(
                    if (isAreTwoGhostOneBand(
                            indexOneGhost = line[1] - 1,
                            indexTwoGhost = line[2] - 1
                        )
                    ) "YES" else "NO"
                )

                3 -> println(
                    getCountBand(
                        indexGhost = line[1] - 1
                    )
                )
            }
        }
    }

    private fun getOthersLine(countIssue: Int): List<String> {
        val lines = mutableListOf<String>()
        for (i in 0 until countIssue) {
            lines.add(readln())
        }
        return lines
    }

    private fun getGhostList(countGhost: Int): List<Ghost> {
        val allGhost = mutableListOf<Ghost>()
        for (i in 0 until countGhost) {
            allGhost.add(
                Ghost(
                    name = i,
                    numberBand = i
                )
            )
        }
        return allGhost
    }

    private fun mergeGHost(indexOneGhost: Int, indexTwoGhost: Int) {
        ghostList[indexOneGhost].countBand += 1

        ghostList[indexTwoGhost].apply {
            numberBand = indexOneGhost
            countBand += 1
        }

        ghostList.partition { it.numberBand == indexTwoGhost }.first.map {
            it.numberBand = indexOneGhost
            it.countBand += 1
        }

    }

    private fun isAreTwoGhostOneBand(indexOneGhost: Int, indexTwoGhost: Int): Boolean {
        return ghostList[indexOneGhost].numberBand == ghostList[indexTwoGhost].numberBand
    }

    private fun getCountBand(indexGhost: Int): Int {
        return ghostList[indexGhost].countBand
    }
}

private data class Ghost(
    val name: Int,
    var countBand: Int = 1,
    var numberBand: Int
)