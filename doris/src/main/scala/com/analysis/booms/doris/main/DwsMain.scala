package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.DateUtils
import com.analysis.booms.doris.dws.{DwsPangleDay, DwsTaDay}

object DwsMain {
  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(41)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    DwsTaDay.runData(startDay, endDay)
    DwsPangleDay.runData(startDay, endDay)
  }
}
