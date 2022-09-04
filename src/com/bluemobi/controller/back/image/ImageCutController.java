package com.bluemobi.controller.back.image;

import javax.imageio.IIOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.to.image.ImageCutTO;
import com.bluemobi.util.ImageUtils;

/**
 * 图片切割功能
 * 
 * @author zhangzheng
 * @date 2015-12-18
 * 
 */
@Controller
@RequestMapping("back/imageCut")
public class ImageCutController extends AbstractBackController {

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String index(Model model) {
        return "back/image/imageCut";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index1(Model model) {
        return "back/image/imageCut";
    }

    @RequestMapping(value = "cutAndSave", method = RequestMethod.GET)
    public String cutAndSave(ImageCutTO cut) {
        try {
            Double multiple = cut.getMultiple();
            Integer x1 = (int) (cut.getX1() * multiple);
            Integer y1 = (int) (cut.getY1() * multiple);
            Integer x2 = (int) (cut.getX2() * multiple);
            Integer y2 = (int) (cut.getY2() * multiple);
            ImageUtils.cutImg(cut.getImageUrl(), x1, y1, x2, y2, cut.getWidth(), cut.getHeight(), cut.getSaveUrl());
        } catch (IIOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cut.getSaveUrl();
    }
}
