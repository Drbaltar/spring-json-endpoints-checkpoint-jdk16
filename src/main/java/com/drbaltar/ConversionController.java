package com.drbaltar;

import com.drbaltar.activity.ActivityViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConversionController {

    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.detailed+json")
    @JsonView(ActivityViews.DetailedView.class)
    public List<ActivityRecord.Activity> getDetailedActivityView(@RequestBody ActivityRecord.Activities inputActivityList) {
        return inputActivityList.activities();
    }

    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.compact+json")
    @JsonView(ActivityViews.CompactView.class)
    public List<ActivityRecord.Activity> getCompactActivityView(@RequestBody ActivityRecord.Activities inputActivityList) {
        return inputActivityList.activities();
    }

    @PostMapping("/activities/mailing-list")
    @JsonView(ActivityViews.MailingList.class)
    public List<ActivityRecord.Activity> generateMailingListFromJSON(@RequestBody ActivityRecord.Activities inputActivityList) {
        return inputActivityList.activities();
    }

}
