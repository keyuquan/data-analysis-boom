package com.analysis.boom.jobs.ta.main

import com.analysis.boom.common.utils.DateUtils
import com.analysis.boom.jobs.ta.dws.DwsTa

object DwsMain {
  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(10)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    DwsTa.runData(startDay, endDay);

  }
}
