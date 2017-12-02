import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("subsets.in").bufferedReader().readLine().toInt()
    val seq = ArrayList<ArrayList<Int>>()

    for (mask in 0 until (1 shl n)) {
        val cur = arrayListOf<Int>()
        (0 until n)
                .filter { ((mask shr it) and 1) > 0 }
                .mapTo(cur) { it + 1 }
        seq.add(cur)
    }
    seq.sortWith(Comparator { o1, o2 ->
        (0 until minOf(o1.size, o2.size))
                .filter { o1[it] != o2[it] }
                .forEach { return@Comparator o1[it].compareTo(o2[it]) }
        if (o1.size == o2.size) 0
        else o1.size.compareTo(o2.size)
    })

    File("subsets.out").bufferedWriter().use { it.write(seq.joinToString("\n") { it.joinToString(" ") }) }
}