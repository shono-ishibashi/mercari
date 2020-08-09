package com.example.demo.myBatisParam;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ページングに関する変数を格納するクラス
 */

@Data
@AllArgsConstructor
public class PagingParam {
    private Integer limit;
    private Integer offset;
}
