package com.joeracosta.covidtracker

import com.joeracosta.covidtracker.data.DataToPlot
import com.joeracosta.covidtracker.data.State
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test


class CovidTest {

    private val covidRobot get() = CovidRobot()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun testUSState() {
        covidRobot
            .setSelectedUSState(State.NEW_JERSEY)
            .assertSelectedState(State.NEW_JERSEY)
            .assertSelectedStateIsStored(State.NEW_JERSEY)
    }

    @Test
    fun testTimeFrame() {
        covidRobot
            .setSelectedTimeFrame(30)
            .assertAmountOfDaysAgoToShow(30)
            .assertAmountOfDaysAgoToShowIsStored(30)
    }

    @Test
    fun testDataToPlot() {
        covidRobot
            .setDatToPlot(DataToPlot.CURRENT_HOSPITALIZATIONS)
            .assertSelectedDataToPlot(DataToPlot.CURRENT_HOSPITALIZATIONS)
            .assertStoredSelectedDataToPlot(DataToPlot.CURRENT_HOSPITALIZATIONS)
    }

    @Test
    fun testRefreshUpdate() {
        covidRobot
            .forceRefresh()
            .assertLastUpdatedTimeWasLessThanAMinuteAgo()
    }
}