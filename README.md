# Davesteppa

Experimental drum machine written using [Clojurescript](https://github.com/clojure/clojurescript). I'm just toying around to learn how Clojurescript works. Don't expect anything polished!

That said [try out the online demo](http://kzar.co.uk/static/davesteppa), it uses the stuff in `public/`. (I made index.html manually, not sure the best way to tie in the Enlive templates for the production version. Especially as Clojure isn't actually required for the demo.)

# Usage

      # Grab everything
      git clone http://github.com/kzar/davesteppa
      cd davesteppa
      lein deps

      # Start Ring webserver with Clojure repl
      lein repl
      (go)
      # Start browser repl
      lein trampoline cljsbuild repl-launch mac
      # Start the Clojurescript auto-builder
      lein cljsbuild auto

(You'll end up having three terminal sessions open; one for the webserver, one for browser repl and one for auto-builder. You can [start the browser repl from Emacs](https://gist.github.com/2623705#comments) if you like though.)

# Aims
 - [x] Get project set up.
 - [x] Get basic drum machine working.
 - [ ] Write code to reset position sometimes.
 - [ ] Figure out how to overlap sounds so we can increase tempo.
 - [ ] Change tempo field to use BPM.
 - [ ] Sort out nice layout.
 - [ ] Confirm usage terms of the sound effects, possibly record new ones.
 - [ ] Set up system for storing and sharing songs.
 - [ ] Spec out a file format for stored songs and allow the raw file to be downloaded.

# License

Code is copyright Dave Barker 2012, provided under GPL version 3

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

Sounds are property of [Tuab Muso](http://www.tubabmuso.nl/english/djembe-samples.htm), I'm unsure as to the terms of use but I'm checking with them.