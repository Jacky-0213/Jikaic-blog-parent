package top.jiakaic.blog.vo.params;

import lombok.Data;

/**
 * @author JK
 * @date 2021/7/26 -9:00
 * @Description
 **/
@Data
public class PageParams {
    private int page = 1;

    private int pageSize = 10;

    private Long categoryId;

    private Long tagId;

    private String year;

    private String month;

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0"+this.month;
        }
        return this.month;
    }
}
