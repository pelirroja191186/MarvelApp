package com.yhq.marvel.core.result

import com.google.common.truth.Truth
import com.yhq.marvel.core.result.Either.Left
import com.yhq.marvel.core.result.Either.Right
import org.junit.*

class EitherTest {

    @Test
    fun `Either Right ok`() {
        val rightValue = "right example"
        val result = Right(rightValue)

        Truth.assertThat(result).isInstanceOf(Either::class.java)
        Truth.assertThat(result.isRight).isTrue()
        Truth.assertThat(result.isLeft).isFalse()

        result.fold({},
            { right ->
                Truth.assertThat(right).isInstanceOf(String::class.java)
                Truth.assertThat(right).isEqualTo(rightValue)
            })
    }

    @Test
    fun `Either Left ok`() {
        val leftValue = "left example"
        val result = Left(leftValue)

        Truth.assertThat(result).isInstanceOf(Either::class.java)
        Truth.assertThat(result.isLeft).isTrue()
        Truth.assertThat(result.isRight).isFalse()

        result.fold(
            { left ->
                Truth.assertThat(left).isInstanceOf(String::class.java)
                Truth.assertThat(left).isEqualTo(leftValue)
            }, {})
    }
}
