from modeller import *
from modeller.scripts import complete_pdb

log.verbose()
env = environ()
env.libs.topology.read(file='$(LIB)/top_heav.lib')
env.libs.parameters.read(file='$(LIB)/par.lib')
aln = alignment(env)
mdl = model(env, file='1bdm', model_segment=('FIRST:A','LAST:A'))
aln.append_model(mdl, align_codes='1bdmA', atom_files='1bdm.pdb')
for (code) in (('TvLDH.BL00080001'), ('TvLDH.B99990001')):
    mdl = complete_pdb(env, code)
    aln.append_model(mdl, atom_files=code, align_codes=code)
aln.align2d()
aln.write(file='TvLDH.BL00080001-1bdmA.ali', alignment_format='PIR')
