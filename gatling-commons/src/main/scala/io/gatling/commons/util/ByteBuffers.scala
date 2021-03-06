/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.commons.util

import java.nio.ByteBuffer

import io.gatling.commons.util.Collections._

object ByteBuffers {

  val Empty: ByteBuffer = ByteBuffer.wrap(Array.empty)

  def byteBuffer2ByteArray(byteBuffer: ByteBuffer): Array[Byte] = {
    val bytes = new Array[Byte](byteBuffer.remaining)
    if (byteBuffer.hasArray) {
      System.arraycopy(byteBuffer.array, byteBuffer.arrayOffset, bytes, 0, bytes.length)
    } else {
      byteBuffer.get(bytes)
    }

    bytes
  }

  def byteBuffers2ByteArray(byteBuffers: Seq[ByteBuffer]): Array[Byte] = {
    val bytes = new Array[Byte](byteBuffers.sumBy(_.remaining))
    var pos = 0
    byteBuffers.foreach { byteBuffer =>
      val remaining = byteBuffer.remaining
      if (byteBuffer.hasArray) {
        System.arraycopy(byteBuffer.array, byteBuffer.arrayOffset, bytes, pos, remaining)
      } else {
        byteBuffer.get(bytes, pos, remaining)
      }
      pos += remaining
    }

    bytes
  }
}
