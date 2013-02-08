from modeller import *
from modeller.scripts import complete_pdb

log.verbose()
env = environ()
env.libs.topology.read(file='$(LIB)/top_heav.lib')
env.libs.parameters.read(file='$(LIB)/par.lib')
env.io.atom_files_directory = './:../atom_files/'

aln = alignment(env)
for (code) in (('TvLDH.BL00080001'), ('TvLDH.B99990001')):
    mdl = complete_pdb(env, code)
    aln.append_model(mdl, atom_files=code, align_codes=code)
aln.write(file='bmodel-loop.ali', alignment_format='PIR')
