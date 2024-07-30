package com.lovevery.exam.flow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lovevery.exam.base.di.providers.ResourceProvider
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.data.repository.SendMessageRepository
import com.lovevery.exam.flow.action.SendMessageAction
import com.lovevery.exam.flow.model.MessageByUserUi
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AddMessageViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: SendMessageRepository

    @Mock
    private lateinit var mockResourcesProvider: ResourceProvider

    @Mock
    private lateinit var showProgress: Observer<Boolean>

    @Mock
    private lateinit var observer: Observer<SendMessageAction>

    private lateinit var viewModel: AddMessageViewModel

    private val uiResponse = MessageByUserUi(
        user = MOCK_USER,
        listMessage = listOf(
            MessageByUserUi.BodyMessage(
                subject = "Exam",
                message = "Coding"
            )
        )
    )

    private val response = MessageByUserResponse(
        statusCode = 200,
        body = "{\"user\": \"Paco\", \"message\": [{\"subject\": \"Exam\", \"message\": \"Coding\"}]}"
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = AddMessageViewModel(
            mockRepository, mockResourcesProvider
        ).apply {
            getShowProgress().observeForever(showProgress)
            getAction().observeForever(observer)
        }
    }

    @Test
    fun `Should emmit ShowListMessageByUser when service finished with success`(){
        getMessageByUser()

        verify(observer).onChanged(
            eq(SendMessageAction.ShowListMessageByUser(uiResponse.listMessage))
        )
    }

    private fun getMessageByUser() {
        whenever(
            mockRepository.getMessageByUser(MOCK_USER)
        ).thenReturn(
            Single.just(uiResponse)
        )

        viewModel.getMessageByName(MOCK_USER)
    }

    private companion object {
        const val MOCK_USER = "mock_user"
    }
}