package com.jay.amateur;

import com.jay.amateur.floatview.ActionBean;

import java.util.ArrayList;
import java.util.List;



public class Config {

    private List<String> serverData = new ArrayList<>();
    private List<ActionBean> maction = new ArrayList<>();

    public List<String> getServerData() {
        return serverData;
    }

    public List<ActionBean> getMaction() {
        return maction;
    }

    private Config(Build build) {
        serverData.addAll(build.serverData);
        maction.addAll(build.maction);
    }


    public static class Build {
        List<String> serverData = new ArrayList<>();
        List<ActionBean> maction = new ArrayList<>();

        public Build(List<String> severd, List<ActionBean> action) {
            serverData.clear();
            if (severd != null)
                serverData.addAll(severd);
            maction.clear();
            if (action != null)
                maction.addAll(action);
        }

        public Config build() {
            return new Config(this);
        }
    }
}
