package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.DateUtils
import com.analysis.booms.doris.app.AppDay

object AppMain {
  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(41)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    AppDay.runData(startDay, endDay);
  }
}
