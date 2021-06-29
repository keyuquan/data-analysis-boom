package com.analysis.boom.jobs.ta.main

import com.analysis.boom.common.utils.DateUtils
import com.analysis.boom.jobs.ta.dwm.DwmTa

object DwmTaMain {
  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(4)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    DwmTa.runData(startDay, endDay);

  }
}
