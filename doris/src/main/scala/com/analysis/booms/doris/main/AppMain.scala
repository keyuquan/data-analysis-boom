package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.DateUtils
import com.analysis.booms.doris.app.AppDay
import com.analysis.booms.doris.dws.{DwsOceanDay, DwsPangleDay, DwsTaDay}

object AppMain {
  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(10)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    AppDay.runData(startDay, endDay)
  }
}
