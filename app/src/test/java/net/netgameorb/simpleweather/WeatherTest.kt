package net.netgameorb.simpleweather

import junit.framework.Assert.assertEquals
import net.netgameorb.simpleweather.DataModels.Models.Coordinate
import org.junit.Test


class WeatherTest
{

    @Test
    fun testCoordinate()
    {
        val coordinate = Coordinate(0.1, 0.2)

        assertEquals("Testing Coordinate Longitude", coordinate.longitude, 0.1)
        assertEquals("Testing Coordinate Latitude", coordinate.latitude, 0.2)
    }
}