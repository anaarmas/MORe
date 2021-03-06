package uk.ac.ox.cs.pagoda.rules;

import java.util.Collection;
import java.util.LinkedList;

import org.semanticweb.HermiT.model.AtLeastDataRange;
import org.semanticweb.HermiT.model.DLClause;

public class OverApproxBoth implements Approximator {
	
	protected Approximator approxDist = new OverApproxDisj();
	Approximator approxExist = new OverApproxExist();
		
	@Override
	public Collection<DLClause> convert(DLClause clause, DLClause originalClause) {
		Collection<DLClause> ret = new LinkedList<DLClause>(); 
		for (DLClause tClause: approxDist.convert(clause, originalClause)) {
			if (tClause.getHeadLength() > 0 && tClause.getHeadAtom(0).getDLPredicate() instanceof AtLeastDataRange) 
				continue; 
			ret.addAll(approxExist.convert(tClause, originalClause)); 
		}
		return ret; 
	}
		
}
