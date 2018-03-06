package com.zfj.xmy.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.zfj.xmy.elasticsearch.document.CategoryDoc;
@Repository
public interface CategoryDocRepository extends ElasticsearchRepository<CategoryDoc,Long> {

}
