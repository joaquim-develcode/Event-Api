package com.example.events

import com.example.events.model.Event
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat

/**
 * @author Joaquim
 */
class EventTest {

    @Test
    fun `test format date day mounth year`() {
        val list = listOf("ABC", "DEF", "GHI")
        var people = list
        val event = Event(
            id = "1",
            title = "TesteEvent",
            description = "DescriptionTest",
            date = 1534784400000,
            image = "",
            latitude = 100.0,
            longitude = -100.0,
            people = people,
            price = 100.0
        )
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val dateString: String = simpleDateFormat.format(event.date)
        Assert.assertEquals("20/08/2018", dateString)
    }

    @Test
    fun `test should format date case null`()  {
        val list = listOf("ABC", "DEF", "GHI")
        var people = list
        val event = Event(
            id = "1",
            title = "TesteEvent",
            description = "DescriptionTest",
            image = "",
            date = null,
            latitude = 100.0,
            longitude = -100.0,
            people = people,
            price = 100.0
        )
        Assert.assertEquals(null, event.date)
    }

}
