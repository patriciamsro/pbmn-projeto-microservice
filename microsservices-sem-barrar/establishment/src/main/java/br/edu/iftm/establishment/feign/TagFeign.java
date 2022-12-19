package br.edu.iftm.establishment.feign;

import br.edu.iftm.establishment.entitie.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "tag")
public interface TagFeign {

    @GetMapping("tags/findcod/{codigo}")
    public Optional<Tag> findByCod(@PathVariable String codigo);
}
