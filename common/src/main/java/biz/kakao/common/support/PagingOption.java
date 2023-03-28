package biz.kakao.common.support;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingOption {

    private int pageNumber;
    private int pageSize;

    public PagingOption(){
        this.pageNumber = 1;
        this.pageSize = 10;
    }

    public PagingOption(int pageSize,
                        int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
