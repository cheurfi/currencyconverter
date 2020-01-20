package com.example.currencyconverter.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.model.CurrencyBase
import com.example.currencyconverter.repository.CurrencyConverterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(CurrencyConverterRepository::class)
class CurrencyConverterViewModelTest {

    private val repository = mock(CurrencyConverterRepository::class.java)
    private val model = CurrencyConverterViewModel(repository)

    @Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun getOrganisations_setsBaseValueAndName() {
        val rates = mapOf(Pair(BASE, RATE_VALUE))
        runBlocking {
            `when`(repository.getCurrencyItems()).thenReturn(CurrencyBase(BASE, DATE, rates))
            model.getOrganisations()
        }
        assertEquals(model.currencyItems.value?.first()?.name, BASE)
        assertEquals(model.currencyItems.value?.first()?.value, BASE_RATE_VALUE)
    }

    @Test
    fun getOrganisations_setsCorrectValues() {
        val rates = mapOf(Pair(RATE, RATE_VALUE))
        runBlocking {
            `when`(repository.getCurrencyItems()).thenReturn(CurrencyBase(BASE, DATE, rates))
            model.getOrganisations()
        }
        assertEquals(model.currencyItems.value?.get(1)?.name, RATE)
        assertEquals(model.currencyItems.value?.get(1)?.value, RATE_VALUE)
    }

    companion object {
        const val BASE = "EUR"
        const val RATE = "SEK"
        const val DATE = "10/07/2020"
        const val BASE_RATE_VALUE = 0.0
        const val RATE_VALUE = 1.7
    }
}