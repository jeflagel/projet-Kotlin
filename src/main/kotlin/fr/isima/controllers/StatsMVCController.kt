package fr.isima.controllers

import fr.isima.views.toHtml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

import javax.inject.Provider

@Controller
@RequestMapping("/stats")
class StatsMVCController @Autowired
constructor(private val statsViewBean: Provider<StatsViewBean>) {

    @GetMapping
    @ResponseBody
    fun stats() = statsViewBean.get().toHtml()
}
