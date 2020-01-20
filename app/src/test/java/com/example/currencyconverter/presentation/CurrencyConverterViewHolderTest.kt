package com.example.currencyconverter.presentation

import android.view.View
import com.example.currencyconverter.model.Rate
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito.mock

class CurrencyConverterViewHolderTest {

    private val view = mock(View::class.java)
    private val listener = mock(CurrencyConverterViewHolder.ItemClickListener::class.java)
    private val viewHolder = CurrencyConverterViewHolder(view, listener)

    @Test
    fun bind_callsListenerWithCorrectRate() {
        val rate = Rate(RATE_NAME, RATE_VALUE)

        viewHolder.bind(rate)

        verify(view).setOnClickListener(any() as View.OnClickListener?)
    }

    companion object {
        const val RATE_NAME = "Euro"
        const val RATE_VALUE = 1.47
    }
}