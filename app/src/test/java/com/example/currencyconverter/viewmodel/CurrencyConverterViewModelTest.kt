package com.example.currencyconverter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.model.CurrencyBase
import com.example.currencyconverter.repository.CurrencyConverterRepository
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.core.classloader.annotations.PrepareForTest

@RunWith(PowerMockRunner::class)
@PrepareForTest(CurrencyConverterRepository::class)
class CurrencyConverterViewModelTest {

    private lateinit var model: CurrencyConverterViewModel
    private val repository = mock(CurrencyConverterRepository::class.java)

    @Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        model = CurrencyConverterViewModel(repository)
    }

    @Test
    fun getOrganisations_setsCorrectLiveData() {
        val rates = mapOf(Pair(RATE_NAME, RATE_VALUE))
        runBlocking {
            `when`(repository.getCurrencyItems()).thenReturn(CurrencyBase(BASE, DATE, rates))
            model.getOrganisations()
        }
        assertEquals(model.currencyItems.value?.base, BASE)
        assertEquals(model.currencyItems.value?.date, DATE)
        assertEquals(model.currencyItems.value?.rates?.keys?.first(), RATE_NAME)
        assertEquals(model.currencyItems.value?.rates?.values?.first(), RATE_VALUE)
    }

    companion object {
        const val BASE = "Eur"
        const val DATE = "10/07/2020"
        const val RATE_NAME = "Euro"
        const val RATE_VALUE = 1.47
    }
}