package com.sksamuel.eel.component.parquet

import java.io.File

import com.fiftyonezero.eel.component.parquet.ParquetSource
import com.fiftyonezero.eel.{FrameSchema, Row, Column}
import com.sksamuel.eel.FrameSchema
import org.apache.hadoop.fs.Path
import org.scalatest.{Matchers, WordSpec}

class ParquetSourceTest extends WordSpec with Matchers {

  "ParquetSource" should {
    "read schema" in {
      val people = ParquetSource(new Path(new File(getClass.getResource("/person.pq").getFile).getAbsolutePath))
      people.schema shouldBe FrameSchema(Seq(Column("name"), Column("job"), Column("location")))
    }
    "read parquet files" in {
      val people = ParquetSource(new Path(new File(getClass.getResource("/person.pq").getFile).getAbsolutePath)).toList
      people shouldBe List(
        Row(Seq(Column("name"), Column("job"), Column("location")), Seq("clint eastwood", "actor", "carmel")),
        Row(Seq(Column("name"), Column("job"), Column("location")), Seq("elton john", "musician", "pinner"))
      )
    }
  }
}

