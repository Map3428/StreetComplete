/*
* This quest was generated by the StreetComplete QuestCreator (https://github.com/ENT8R/StreetCompleteQuestCreator)
*/

package de.westnordost.streetcomplete.quests.bench_backrest;

import android.os.Bundle;

import java.util.Map;

import javax.inject.Inject;

import de.westnordost.streetcomplete.R;
import de.westnordost.streetcomplete.data.osm.SimpleOverpassQuestType;
import de.westnordost.streetcomplete.data.osm.changes.StringMapChangesBuilder;
import de.westnordost.streetcomplete.data.osm.download.OverpassMapDataDao;
import de.westnordost.streetcomplete.quests.AbstractQuestAnswerFragment;
import de.westnordost.streetcomplete.quests.YesNoQuestAnswerFragment;

public class AddBenchBackrest extends SimpleOverpassQuestType
{
	@Inject public AddBenchBackrest(OverpassMapDataDao overpassServer) { super(overpassServer); }

	@Override protected String getTagFilters() { return "nodes with amenity=bench and !backrest"; }

	public AbstractQuestAnswerFragment createForm() { return new BenchBackrestForm(); }

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
        boolean isPicnicTable = answer.getBoolean(BenchBackrestForm.PICNIC_TABLE);

		if (isPicnicTable) {
            changes.add("leisure", "picnic_table");
            changes.delete("amenity");
        } else {
            String yesno = answer.getBoolean(YesNoQuestAnswerFragment.ANSWER) ? "yes" : "no";
            changes.add("backrest", yesno);
        }
	}

	@Override public String getCommitMessage() { return "Add backrest information to benches"; }
	@Override public int getIcon() { return R.drawable.ic_quest_toilets; }
	@Override public int getTitle(Map<String, String> tags) { return R.string.quest_bench_backrest_title; }
}
