package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by yaweiw on 7/6/2017.
 */
class Widget
{
    public String color;
    public int d1;
    public int d2;
    public Widget(String _color, int _d1, int _d2)
    {
        color = _color;
        d1 = _d1;
        d2 = _d2;
    }
}

@RestController
public class WidgetController
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ArrayList<Widget> widgets = new ArrayList<Widget>();

    public WidgetController()
    {
        if(widgets != null && widgets.isEmpty()) {
            widgets.add(0, new Widget("green", 1, 2));
            widgets.add(1, new Widget("brown", 3, 4));
            widgets.add(2, new Widget("red", 5, 6));
            widgets.add(3, new Widget("white", 7, 8));
        }
    }
    @RequestMapping(value="/api/{index}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Widget getWidget(@PathVariable("index") int index) {
        return widgets.get(index);
    }
}
