package com.zfj.xmy.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.zfj.xmy.elasticsearch.document.GoodsDoc;
@Repository
public interface GoodsDocRepository extends ElasticsearchRepository<GoodsDoc,Long> {

}
