package bll.services;

import bll.entities.Isin;
import bll.utils.ElvlCache;
import bll.utils.Utils;
import dal.entities.IsinElvl;
import dal.interfaces.ElvlRepository;
import dal.interfaces.HistoryRepository;

import java.util.List;

public class IsinElvlService {
    private final ElvlRepository elvlRepository;
    private final HistoryRepository historyRepository;
    private final ElvlCache elvlCache;

    public IsinElvlService(ElvlRepository elvlRepository, HistoryRepository historyRepository) {
        this.elvlRepository = elvlRepository;
        this.historyRepository = historyRepository;
        elvlCache = new ElvlCache();
    }

    public boolean addIsin(Isin item) {
        historyRepository.save(Utils.isin2history(item));
        addElvl(item);
        return true;
    }

    // TODO from cache or db/service
    public Double getElvl(String isin) {
        return null;
    }

    public List<IsinElvl> getElvls() {
        return null;
    }

    // TODO: each type of isin can be processed in parallel
    // and maybe queue for 
    private void addElvl(Isin item) {
        Double elvl = elvlCache.updateElvl(item);
        if (elvl == null) throw new NullPointerException("elvl is null");
        elvlRepository.save(new IsinElvl(item.getIsin(), elvl));
    }
}
