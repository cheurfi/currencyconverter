package com.example.currencyconverter.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.model.CurrencyBase
import com.example.currencyconverter.network.CurrencyConverterClient
import com.example.currencyconverter.network.CurrencyConverterService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Response

@RunWith(PowerMockRunner::class)
@PrepareForTest(CurrencyConverterClient::class, CurrencyBase::class)
class CurrencyConverterRepositoryTest {

    private lateinit var repository: CurrencyConverterRepository
    private val client = mock(CurrencyConverterClient::class.java)

    @Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = CurrencyConverterRepository(client)
    }

    @Test
    fun getCurrencyItems_withNegativeResponse_returnsNull() {
        val service = mock(CurrencyConverterService::class.java)
        `when`(client.service).thenReturn(service)
        val responseBody = mock(ResponseBody::class.java)
        val response: Response<CurrencyBase> = Response.error(400, responseBody)
        val completableDeferred = CompletableDeferred(response)
        `when`(client.service.getCurrencyItems()).thenReturn(completableDeferred)

        runBlocking {
            val result = (repository.getCurrencyItems())
            assertEquals(result, null)
        }
    }

    @Test
    fun getCurrencyItems_withSuccessfulResponse_returnsResponse() {
        val service = mock(CurrencyConverterService::class.java)
        `when`(client.service).thenReturn(service)
        val responseBody = mock(CurrencyBase::class.java)
        val response: Response<CurrencyBase> = Response.success(responseBody)
        val completableDeferred = CompletableDeferred(response)
        `when`(client.service.getCurrencyItems()).thenReturn(completableDeferred)

        runBlocking {
            val result = (repository.getCurrencyItems())
            assertEquals(result, response.body())
        }
    }
}