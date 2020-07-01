package com.example.sensyneapp.ui.main

import com.example.sensyneapp.data.DataManager
import com.example.sensyneapp.data.TestSchedulerProvider
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.ui.main.fragment.MainNavigator
import com.example.sensyneapp.ui.main.fragment.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private lateinit var testScheduler: TestScheduler

    @RelaxedMockK
    lateinit var mainNavigator: MainNavigator

    private var dataManager = mockk<DataManager>()

    private val bioHospital = mockk<Hospital> {
        every { subType } returns "Bio Hospital"
    }

    private val mentalHospital = mockk<Hospital> {
        every { subType } returns "Mental Hospital"
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        viewModel = MainViewModel(dataManager, testSchedulerProvider)
        viewModel.setNavigator(mainNavigator)
    }

    @Test
    fun `load data`() {
        every { dataManager.getApiHelper().loadCSV() } returns Single.just (
            listOf(bioHospital, mentalHospital)
        )

        viewModel.loadData()
        testScheduler.triggerActions()

        val captureData = slot<List<Hospital>>()

        verify(exactly = 1) { mainNavigator.showHospitals(capture(captureData)) }
        captureData.captured.let {
            Assert.assertEquals(listOf(bioHospital, mentalHospital), it)
        }
    }
}