# biotea-annotation
Refactorization for the annotation code at https://github.com/alexgarciac/biotea.
RDF annotation for PubMed and PMC using entity recognition tools such as the [NCBO Annotator](http://www.bioontology.org/annotator-service) (http://www.bioontology.org/annotator-service) and CMA (http://journal.sepln.org/sepln/ojs/ojs/index.php/pln/article/view/810/664). CMA is not a public service thus this documentation refers to annotations with [NCBO Annotator](http://www.bioontology.org/annotator-service)

## Dependencies
Most of the dependendies are configured with Maven. There is however a couple of local dependencies to [biotea-utilities](https://github.com/biotea/biotea-utilities), [biotea-ao](https://github.com/biotea/biotea-ao) and one jar located at the lib directory provided with this project.

This project uses the [NCBO Annotator](http://data.bioontology.org/documentation#annotator) thus annotations obtained for the same file at different times can vary due to changes in the ontologies and responses retrieved from the annotator.

## How run this project using the batch option
* Clone [biotea-utilities](https://github.com/biotea/biotea-utilities)
* Clone [biotea-ao](https://github.com/biotea/biotea-ao)
* Clone this repository
* In your IDE, create a dependency from this project to [biotea-utilities](https://github.com/biotea/biotea-utilities) and [biotea-ao](https://github.com/biotea/biotea-ao) and jars in the lib directory
* Modify configuration files, i.e., config.properties, in [biotea-utilities](https://github.com/biotea/biotea-utilities) resources folder (path-to-biotea-utilities/src/main/resources/config.properties). If you are generating annotations for RDFized articles with [biotea-rdfization](https://github.com/biotea/biotea-rdfization), make sure you use the same configuration there. Most of the time you only need to change the following properties:
  * biotea.dataset.prefix: Either __pmc__ or __pubmed__
  * biotea.dataset: For instance __dataset/pmc__ or __dataset/pubmed__ or __bio2rdf_dataset:bio2rdf-pmc-vrX__ or __bio2rdf_dataset:bio2rdf-pubmed-vrX__. This will be used in the VOiD properties of the generated dataset.
  * biotea.base: For instance __biotea.ws__ or __bio2rdf.org__. This will be used to generate the URI to resources. bio2rdf will generate URIs compatible with Bio2RDF URI style.
  * ncbo.annotator.exclude: Aliases for those ontologies that should not be used by the [NCBO Annotator](http://www.bioontology.org/annotator-service). All the aliases are defined as properties at path-to-biotea-utilities/src/main/resources/ontologies.properties.
* Specify a valid API-KEY to use the [NCBO Annotator](http://www.bioontology.org/annotator-service) or the [AgroPortal annotator](http://agroportal.lirmm.fr/annotator) at path-to-biotea-utilities/src/main/resources/apikey.properties
* Make sure you include the [biotea-utilities](https://github.com/biotea/biotea-utilities) resources folder in your classpath
* The main class is ws.biotea.ld2rdf.annotation.batch.BatchApplication some parameters are needed:
  * -in <input-dir> --mandatory, should point to a directory with all the files to be annotated
  * -out <output-dir> --mandatory
  * -annotator --optional, use __ncbo__ (default value) or __agroportal__.
  * -extension --mandatory, only files at <input-dir> with this extension will be processed, either __nxml__ or __rdf__ is our recommendation
  * -inStyle --optional, either __jats_file__ (default value) or __rdf_file__
  * -onto --optional, either __ao__ for the [Annotation Ontology](https://github.com/annotation-ontology/annotation-ontology) or __oa__ for the [Open Annotation](http://www.openannotation.org/spec/core/), this defines the annotation ontology used to serialize the annotations
  * -format --optional, either __XML__ (default value) or __JSON-LD__
  * -onlyTA --optional, if present, ontly title and abstract will be annotated
 
### Input
If jats_file is used as inStyle option:
* Input files should follow the [JATS](https://jats.nlm.nih.gov/) DTDs
* In order to be able to process input articles in batch, those should be located in a folder as the one provided at [nxmlInputToProcess](https://github.com/biotea/biotea-rdfization/tree/master/src/main/resources/nxmlInputToProcess).
* A valid JATS input file is provided at [nxmlInputToProcess/DELETE_ME_PMC3879346.nxml](https://github.com/biotea/biotea-annotation/blob/master/src/main/resources/nxmlInputToProcess/DELETE_ME_PMC3879346.nxml) 
* The corresponding output following the [AO](https://github.com/annotation-ontology/annotation-ontology) notation is provided at [output/PMC3879346_ncbo_annotations_AO_JATS.rdf](https://github.com/biotea/biotea-annotation/blob/master/src/main/resources/output/PMC3879346_ncbo_annotations_AO_JATS.rdf)
* The corresponding output following the [OA](http://www.openannotation.org/spec/core/) notation is provided at [output/PMC3879346_ncbo_annotations_OA_JATS.rdf](https://github.com/biotea/biotea-annotation/blob/master/src/main/resources/output/PMC3879346_ncbo_annotations_OA_JATS.rdf)

If rdf_file is used as inStyle option: 
* Input files should correspond to output files generated by [biotea-rdfization](https://github.com/biotea/biotea-rdfization)
* Only RDF files corresponding to sections should be used
* A valid RDF input file is provided at [rdfInputToProcess/PMC3879346_sections.rdf](https://github.com/biotea/biotea-annotation/blob/master/src/main/resources/rdfInputToProcess/PMC3879346_sections.rdf)
* The corresponding output following the [AO](https://github.com/annotation-ontology/annotation-ontology) notation is provided at [output/PMC3879346_ncbo_annotations_AO_RDF.rdf](https://github.com/biotea/biotea-annotation/blob/master/src/main/resources/output/PMC3879346_ncbo_annotations_AO_RDF.rdf)
* The corresponding output following the [OA](http://www.openannotation.org/spec/core/)notation is provided at [output/PMC3879346_ncbo_annotations_OA_RDF.rdf](https://github.com/biotea/biotea-annotation/blob/master/src/main/resources/output/PMC3879346_ncbo_annotations_OA_RDF.rdf)

### Output
* One RDF file per input file

###Examples
For instance, if you want to annotate PMC articles following the [Bio2RDF URL model](https://github.com/bio2rdf/bio2rdf-scripts/wiki/RDFization-Guide) you need this configuration:
* biotea.dataset.prefix=pmc
* biotea.dataset=bio2rdf_dataset:bio2rdf-pmc-vr2
* biotea.base=bio2rdf.org
Remember to specify a valid API KEY in apikey.properties

If you want to annotate JATS files with extension nxml and get RDF/XML files following AO model use:
* java ws.biotea.ld2rdf.annotation.batch.BatchApplication -in <input-dir> -out <output-dir> -extension nxml
which is equivalent to the following that also specify all parameters with default values
* java ws.biotea.ld2rdf.annotation.batch.BatchApplication -in <input-dir> -out <output-dir> -extension nxml -inStyle jats_file -annotator ncbo -onto ao -format XML
 
If you want to annotate RDF files with extension rdf and get RDF/XML files following AO model use:
* java ws.biotea.ld2rdf.annotation.batch.BatchApplication -in <input-dir> -out <output-dir> -extension rdf -inStyle rdf_file
which is equivalent to the following that also specify all parameters with default values
* java ws.biotea.ld2rdf.annotation.batch.BatchApplication -in <input-dir> -out <output-dir> -extension nxml -inStyle rdf_file -annotator ncbo -onto ao -format XML

If you want to annotate JATS files with extension nxml and get RDF/XML files following OA model use:
* java ws.biotea.ld2rdf.annotation.batch.BatchApplication -in <input-dir> -out <output-dir> -extension nxml -onto OA
which is equivalent to the following that also specify all parameters with default values
* java ws.biotea.ld2rdf.annotation.batch.BatchApplication -in <input-dir> -out <output-dir> -extension nxml -inStyle jats_file -annotator ncbo -onto OA -format XML

