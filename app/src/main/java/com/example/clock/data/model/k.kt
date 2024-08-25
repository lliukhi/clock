import com.example.clock.Location
import java.io.BufferedReader
import java.io.FileReader
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset


fun main() {
    val bufferedReader =
        BufferedReader(FileReader("D:\\AndroidStudioProjects\\Clock\\app\\src\\main\\java\\com\\example\\clock\\la.txt"))
    var line: String?
     var arrays = ArrayList<Location>()
    var i = 0
    while (bufferedReader.readLine().also { line = it } != null) {
        val country = bufferedReader.readLine() // 读取国家名
        if (line != null && country != null) {
            val add = arrays.add(Location( country,line!!))

        }

    }
    bufferedReader.close()
    arrays?.forEach { array ->
        val zoneOffset = ZoneOffset.of(array.name.substring(3))


        // 获取当前时间
        val currentTime = LocalDateTime.now()
        val zonedTime: OffsetDateTime? = currentTime.atOffset(zoneOffset)
        val instant = Instant.parse(zonedTime.toString())





        // 打印当前时间
        println(" $zonedTime")
        println(array) }
}