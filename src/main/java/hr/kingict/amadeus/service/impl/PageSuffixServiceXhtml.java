package hr.kingict.amadeus.service.impl;

import hr.kingict.amadeus.service.PageSuffixService;
import org.springframework.stereotype.Service;

@Service("xhtml")
public class PageSuffixServiceXhtml implements PageSuffixService {

    @Override
    public String getSuffix() {
        return "xhtml";
    }
}
