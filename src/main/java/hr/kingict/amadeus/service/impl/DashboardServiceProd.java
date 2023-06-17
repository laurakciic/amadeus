package hr.kingict.amadeus.service.impl;

import hr.kingict.amadeus.service.DashboardService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class DashboardServiceProd implements DashboardService {

    @Override
    public String getDashboard() {
        return "dashboardProd";
    }
}
