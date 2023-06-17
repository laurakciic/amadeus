package hr.kingict.amadeus.service.impl;

import hr.kingict.amadeus.service.PageSuffixService;
import org.springframework.stereotype.Service;

@Service("html")
public class PageSuffixServiceHtml implements PageSuffixService {

    @Override
    public String getSuffix() {
        return "html";
    }
}
